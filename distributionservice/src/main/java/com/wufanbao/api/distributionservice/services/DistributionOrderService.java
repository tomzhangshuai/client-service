package com.wufanbao.api.distributionservice.services;

import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.CodeException;
import com.wufanbao.api.distributionservice.dao.*;
import com.wufanbao.api.distributionservice.entities.*;
import com.wufanbao.api.distributionservice.rabbitMQ.Sender;
import com.wufanbao.api.distributionservice.transfer.DistributingOrderDetail;
import com.wufanbao.api.distributionservice.transfer.DistributionOrderInfo;
import com.wufanbao.api.distributionservice.transfer.DistributionProductLineInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class DistributionOrderService {

    @Autowired
    DistributionOrderDao distributionOrderDao;

    @Autowired
    SupplementOrderDao supplementOrderDao;

    @Autowired
    DistributionProductLineDao distributionProductLineDao;

    @Autowired
    DistributionOrderLineDao distributionOrderLineDao;

    @Autowired
    SupplementOrderLineDao supplementOrderLineDao;

    @Autowired
    private Sender sender;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取配送员正在配送的配送单（包含未配送及配送中）
     *
     * @param employeeId 配送员ID
     * @return 配送员正在配送的配送单
     */
    public List<DistributionOrderInfo> getDistributingOrders(long employeeId) {
        return distributionOrderDao.getDistributingOrdersByEmployeeId(employeeId);
    }


    /**
     * 获取配送员已配送历史的配送单
     *
     * @param employeeId 配送员ID
     * @return 配送员正在配送的配送单
     */
    public List<DistributionOrderInfo> getHistoryOrders(long employeeId) {
        return distributionOrderDao.getHistoryOrdersByEmployeeId(employeeId);
    }


    /**
     * 出发
     *
     * @param employeeId          配送员
     * @param distributionOrderId 配送单ID
     * @throws Exception
     */
    public void setOut(long employeeId, long distributionOrderId) throws Exception {

        int count = distributionOrderDao.queryDistributingOrders(employeeId, distributionOrderId);

        if (count > 0) {
            //存在未完成的配送单
            throw new CodeException(Code.dataError.getCode(), "存在未完成的配送单");
        }

        int changed = distributionOrderDao.setOut(distributionOrderId);
        if (changed < 1) {
            //不存在配送单或者状态已经改变
            throw new CodeException(Code.dataInvaild.getCode(), "配送单状态已改变");
        }
    }

    /**
     * 终止
     * 配送途中发生异常：车辆故障，机器故障（在其他机器补货完成）
     * 将配送单 "是否配送异常中止" 修改为：true
     * 1、检查配送单是否存在下货补货中的补货单；
     * 2、如果存在下货补货单，A）补货单已经开始补货，终结补货单，B）补货单未开始补货，还原补货单到下货前；
     * 3、计算退回数量，返回车辆库存
     * 4、设置未配送的配送单明细为终止（包含当前下货补货单）
     * 5、设置配送单状态到返仓确认，并设置终止标志
     *
     * @param order 配送单
     * @return
     */
    @Transactional(rollbackFor = CodeException.class)
    public void terminate(DistributionOrder order) throws Exception {
        //SupplementStatus> 4 AND s.SupplementStatus<10
        List<SupplementOrder> distributingSupplementOrders = distributionOrderLineDao.getDistributingSupplementOrder(order.getDistributionOrderId());
        int updateCount = 0;
        if (distributingSupplementOrders.size() > 0) {
            //存在配送中补货单
            SupplementOrder supplementOrder = distributingSupplementOrders.get(0);
            boolean isDum = false;
            if (supplementOrder.getSupplementStatus() != 6) {
                //已开始补货
                updateCount = supplementOrderDao.setSupplementStatus(supplementOrder.getSupplementOrderId(), 10);
            } else {
                //未开始补货,仅下货。退回数量，防止紊乱。
                updateCount = supplementOrderDao.setSupplementStatus(supplementOrder.getSupplementOrderId(), 5);
                isDum = true;
            }
            if (updateCount < 1) {
                throw new CodeException(Code.dataInvaild.getCode(), "配送单补货数据错误或补货状态已改变无法终止");
            }
            List<SupplementOrderLine> supplementOrderLines = supplementOrderLineDao.getSupplementOrderLines(supplementOrder.getSupplementOrderId());
            int surplus = 0;
            for (SupplementOrderLine ol : supplementOrderLines) {
                surplus = ol.getDumpQuantity() - ol.getActualQuantity();
                if (surplus < 1) {
                    continue;
                }
               /* UPDATE DistributionProductLine SET ActualQuantity=ActualQuantity+#{actualQuantity},SurplusQuantity=SurplusQuantity+#{backQuantity},ExtraQuantity=ExtraQuantity+#{backQuantity},ExceptionQuantity=ExceptionQuantity+#{exceptionQuantity} where
                distributionOrderId=#{distributionOrderId} and ProductGlobalId=#{productGlobalId}*/
                updateCount = distributionProductLineDao.shelve(order.getDistributionOrderId(), ol.getProductGlobalId(), ol.getActualQuantity(), surplus, 0);
                if (isDum) {
                    ol.setBackQuantity(0);
                    ol.setDumpQuantity(0);
                } else {
                    ol.setBackQuantity(surplus);
                }
               /* UPDATE supplementorderline SET BackQuantity=#{backQuantity},DumpQuantity=#{dumpQuantity}
                WHERE SupplementOrderId=#{supplementOrderId} AND ProductGlobalId=#{productGlobalId}*/
                updateCount = supplementOrderLineDao.updateOrderLineForTermination(ol);
            }
            try {
                long supplementOrderId = supplementOrder.getSupplementOrderId();
                long machineId = supplementOrder.getMachineId();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("MachineId", String.valueOf(machineId));
                jsonObject.put("SupplementOrderId", String.valueOf(supplementOrder.getSupplementOrderId()));
                sender.supplementCompleted(jsonObject.toJSONString());
                logger.info("---------------------------------------------------------------------------------");
                logger.info("补货单id"+supplementOrderId+":"+machineId);
            }catch(Exception e){
                logger.info("*****************************************************************");
            }
        }
        //修改配送单状态
        /*
        update distributionorder set Status=6,isTermination=1,remark=#{remark},EndDistributeTime=CURRENT_TIMESTAMP where
        DistributionOrderId=#{distributionOrderId} and Status&lt;6
        */
        updateCount = distributionOrderDao.terminate(order.getDistributionOrderId(), order.getRemark());
        if (updateCount < 1) {
            throw new CodeException(Code.dataInvaild.getCode(), "配送单不存在或配送单状态已改变无法终止");
        }
        //修改配送单关系表
        updateCount = distributionOrderLineDao.terminateLine(order.getDistributionOrderId());
    }

    /**
     * 获取确认数据
     *
     * @param distributionOrderId
     * @return
     */
    public List<DistributionProductLineInfo> getConfirmData(long distributionOrderId) {
        return distributionProductLineDao.getConfirmData(distributionOrderId);
    }

    /**
     * 获取配送中的订单详情
     *
     * @param distributionOrderId
     * @return
     */
    public List<DistributingOrderDetail> getDistributingOrderDetail(long distributionOrderId) {
        return distributionOrderLineDao.getDistributingOrderDetail(distributionOrderId);
    }

    /**
     * 获取配送中的订单的配送数量及仓库信息
     *
     * @param distributionOrderId
     * @return
     */
    public Object getDistributingOrderOtherInfo(long distributionOrderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        DistributionProductLine distributionProductLine = distributionProductLineDao.getDistributingQuantity(distributionOrderId);
        Store store = distributionOrderDao.getStoreInfo(distributionOrderId);
        map.put("distributionProductLine", distributionProductLine);
        map.put("store", store);
        return map;
    }


    /**
     * 设置已配送
     *
     * @param distributionOrderId
     */
    public void setDistributed(long distributionOrderId) throws Exception {

        int undistributedCount = distributionOrderLineDao.getUndistributedCount(distributionOrderId);

        if (undistributedCount > 1) {
            throw new CodeException(Code.dataError.getCode(), "还存在未配送的补货单");
        }

        int changed = distributionOrderDao.setDistributed(distributionOrderId);
        if (changed < 1) {
            throw new CodeException(Code.dataInvaild.getCode(), "配送单状态已经改变");
        }
    }

    /**
     * 返仓确认配送完成
     *
     * @param distributionOrderId 配送单ID
     * @param lines               异常数据信息
     */
    public void complete(long distributionOrderId, DistributionProductLine[] lines) throws Exception {
        List<DistributionProductLine> dbLines = distributionProductLineDao.getOrderProductLines(distributionOrderId);

        DistributionProductLine line = null;
        int totalSurplus = 0;
        int changed = 0;
        for (DistributionProductLine dbLine : dbLines) {
            for (DistributionProductLine l : lines) {
                if (l.getProductGlobalId() == dbLine.getProductGlobalId()) {
                    line = l;
                    break;
                }
            }
            if (line == null) {
                continue;
            }
            if (line.getExceptionQuantity() == dbLine.getExceptionQuantity()) {
                continue;//异常数据未修改
            }
            totalSurplus = dbLine.getQuantity() - dbLine.getActualQuantity();//总剩余数=总配送数-实际配送数
            if (totalSurplus < line.getExceptionQuantity()) {
                throw new CodeException(Code.dataError.getCode(), "存在异常数量大于可用数量的记录");
            }

            dbLine.setExceptionQuantity(line.getExceptionQuantity());//异常数量
            dbLine.setSurplusQuantity(totalSurplus - line.getExceptionQuantity());//修改剩余可用数量=总剩余数-异常数量

            changed = distributionProductLineDao.modifyByConfirmDistribution(dbLine);
            if (changed < 1) {
                throw new CodeException(Code.dataError.getCode(), "配送单商品明细修改失败");
            }
        }

        changed = distributionOrderDao.complete(distributionOrderId);

        if (changed < 1) {
            throw new CodeException(Code.dataInvaild.getCode(), "配送单状态已经改变");
        }
    }
}


