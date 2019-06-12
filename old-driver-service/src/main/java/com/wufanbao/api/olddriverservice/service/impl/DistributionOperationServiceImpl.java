package com.wufanbao.api.olddriverservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.olddriverservice.dao.DistributionOrderDao;
import com.wufanbao.api.olddriverservice.dao.DistributionProductLineDao;
import com.wufanbao.api.olddriverservice.dao.EmployeeDao;
import com.wufanbao.api.olddriverservice.dao.SupplementOrderSureDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.DistributionOperationService;
import com.wufanbao.api.olddriverservice.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DistributionOperationServiceImpl implements DistributionOperationService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DistributionOrderDao distributionOrderDao;
    @Autowired
    private SupplementOrderSureDao supplementOrderSureDao;

    @Autowired
    private DistributionProductLineDao distributionProductLineDao;
    @Autowired
    private ResponseInfo responseInfo;

    /**
     * 出发
     * 配送人员开始配送
     * 将配送单状态修改为：配送中-5
     *
     * @param distributionOrder 配送单信息
     * @return
     */
    @Override
    public ResponseInfo beganDelivery(DistributionOrder distributionOrder) {
        //判断员工有没有未完成的配送单
        List<DistributionOrder> distributionOrderList = distributionOrderDao.checkUnfinishedDelivery(distributionOrder.getDistributeEmployeeId());
        if (distributionOrderList.size() > 0) {
            responseInfo.setCode(2021);//您还有未完成的订单
            return responseInfo;
        }
        //修改配送单状态
        int count = distributionOrderDao.depart(distributionOrder.getDistributionOrderId(), 5);
        if (count > 0) {
            responseInfo.setCode(1);//出发成功
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseInfo.setCode(2022);//出发失败
        }
        return responseInfo;
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
     * @param distributionOrder
     * @return
     */
    @Override
    public ResponseInfo terminationDistribution(DistributionOrder distributionOrder) {
        Map map = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            List<SupplementOrder> distributingSupplementOrders = distributionOrderDao.getDistributingSupplementOrder(distributionOrder.getDistributionOrderId());
            int updateCount = 0;
            if (distributingSupplementOrders.size() > 0) {
                //存在配送中补货单
                SupplementOrder supplementOrder = distributingSupplementOrders.get(0);
                boolean isDum = false;
                if (supplementOrder.getSupplementStatus() != 6) {
                    //已开始补货
                    updateCount = supplementOrderSureDao.setSupplementStatus(supplementOrder.getSupplementOrderId(), 10);
                } else {
                    //未开始补货,仅下货
                    updateCount = supplementOrderSureDao.setSupplementStatus(supplementOrder.getSupplementOrderId(), 5);
                    isDum = true;
                }
                if (updateCount < 1) {
                    throw new ServiceException("配送单补货数据错误或补货状态已改变无法终止");
                }
                List<SupplementOrderLine> supplementOrderLines = supplementOrderSureDao.getSupplementOrderLines(supplementOrder.getSupplementOrderId());
                int surplus = 0;
                for (SupplementOrderLine ol : supplementOrderLines) {
                    surplus = ol.getDumpQuantity() - ol.getActualQuantity();
                    if (surplus < 1) {
                        continue;
                    }
                    updateCount = distributionProductLineDao.increaseSurplusQuantity(distributionOrder.getDistributionOrderId(), ol.getProductGlobalId(), surplus);
                    if (isDum) {
                        ol.setBackQuantity(0);
                        ol.setDumpQuantity(0);
                    } else {
                        ol.setBackQuantity(surplus);
                    }
                    updateCount = supplementOrderSureDao.updateOrderLineForTermination(ol);
                }
            }

            //修改配送单状态
            updateCount = distributionOrderDao.TerminationDeliver(distributionOrder.getDistributionOrderId(), distributionOrder.getRemark());
            if (updateCount < 1) {
                throw new ServiceException("配送单不存在或配送单状态已改变无法终止");
            }
            //修改配送单关系表
            updateCount = distributionOrderDao.TerminationLine(distributionOrder.getDistributionOrderId());


        } catch (ServiceException ex) {
            responseInfo.setCode(2031);
            responseInfo.setMessage(ex.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseInfo;
        } catch (Exception ex) {
            responseInfo.setCode(2031);
            responseInfo.setMessage("发生未知错误");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return responseInfo;
        }

        map.put("returnState", 1);//终止成功
        responseInfo.setCode(1);
        return responseInfo;
    }

    /**
     * 确认订单信息
     * 获取配送单总的商品补货数据
     *
     * @param distributionOrder 配送单信息
     * @return
     */
    @Override
    public ResponseInfo confirmDeliveryOrder(DistributionOrder distributionOrder) {
        ResponseInfo<List<ConfirmDeliveryOrderInfo>> responseInfo = new ResponseInfo();
        //配送单信息
        List<ConfirmDeliveryOrderInfo> confirmDeliveryOrderInfoList = distributionOrderDao.confirmDeliveryOrder(distributionOrder.getDistributionOrderId());
        if (confirmDeliveryOrderInfoList.size() > 0) {
            for (int i = 0; i < confirmDeliveryOrderInfoList.size(); i++) {
                //异常数量
                int exceptionQuantity = confirmDeliveryOrderInfoList.get(i).getExceptionQuantity();
                //数据库不含异常数的剩余数量
                int surplusQuantity = confirmDeliveryOrderInfoList.get(i).getSurplusQuantity();
                //前台展示的包含异常数的剩余数量
                surplusQuantity = exceptionQuantity + surplusQuantity;
                confirmDeliveryOrderInfoList.get(i).setSurplusQuantity(surplusQuantity);
            }
            responseInfo.setCode(1);
            responseInfo.setData(confirmDeliveryOrderInfoList);
            return responseInfo;
        }
        responseInfo.setCode(2041);
        responseInfo.setData(confirmDeliveryOrderInfoList);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return responseInfo;
    }

    /**
     * 确认订单
     * 当配送单配送完成后，需要配送人员来确认
     * 将配送单状态修改为：返库中-7
     *
     * @param distributionOrder
     * @return
     */
    @Override
    public ResponseInfo confirmAnOrder(DistributionOrder distributionOrder, String quantityGather) {
        ResponseInfo responseInfo = new ResponseInfo();
        List<Quantity> quantityList = JSON.parseArray(quantityGather, Quantity.class);
        for (int i = 0; i < quantityList.size(); i++) {
            //商品ID
            long productGlobalId = quantityList.get(i).getProductGlobalId();
            //异常数量
            int exceptionQuantity = Integer.valueOf(quantityList.get(i).getExceptionQuantity());

            DistributionProductLine distributionproductline1 = new DistributionProductLine();
            distributionproductline1.setProductGlobalId(productGlobalId);
            distributionproductline1.setExceptionQuantity(exceptionQuantity);

            //获取配送单异常数
            List<DistributionProductLine> distributionproductlines = supplementOrderSureDao.queryDistributionproductline(distributionOrder.getDistributionOrderId(), productGlobalId);
            int disExceptionQuantity = distributionproductlines.get(0).getExceptionQuantity();//异常数
            int disQuantity = distributionproductlines.get(0).getQuantity();//配送总数
            int disActualQuantity = distributionproductlines.get(0).getActualQuantity();//实际补货数
            int disSurplusQuantity = distributionproductlines.get(0).getSurplusQuantity();//剩余数

            int c = disExceptionQuantity - exceptionQuantity;
            int newSurplusQuantity = disSurplusQuantity + c;
            if (newSurplusQuantity <= 0) {
                newSurplusQuantity = 0;
            }
            if (disQuantity == exceptionQuantity + disActualQuantity + newSurplusQuantity) {
                distributionproductline1.setSurplusQuantity(newSurplusQuantity);
            }

            distributionproductline1.setDistributionOrderId(distributionOrder.getDistributionOrderId());
            supplementOrderSureDao.updateDistributionproductline(distributionproductline1);
        }
        //修改配送单状态
        int count = distributionOrderDao.confirmDeliverStatus(distributionOrder.getDistributionOrderId(), 7);
        if (count > 0) {
            responseInfo.setCode(1);
            return responseInfo;
        }
        responseInfo.setCode(2051);
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return responseInfo;
    }

    /**
     * 根据员工工号获取配送单信息
     *
     * @param employeeId
     * @return
     */
    @Override
    public ResponseInfo getDistributionOrderByEmployeeId(long employeeId) {
        Map map = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        Employee employee = employeeDao.getEmployeeById(employeeId);
        List<DistributionOrderInfo> distributionOrderInfoList = distributionOrderDao.getDistributionOrderByEmployeeId(employeeId);
        for (int i = 0; i < distributionOrderInfoList.size(); i++) {
            //获取订单货物数量放到配送单信息
            distributionOrderInfoList.get(i).setCargoQuantity(distributionOrderDao.getCargoQuantity(distributionOrderInfoList.get(i).getDistributionOrderId()));
            //获取机器数量放到配送单信息
            distributionOrderInfoList.get(i).setDeviceNumber(distributionOrderDao.getDeviceNumber(distributionOrderInfoList.get(i).getDistributionOrderId()));
        }
        if (distributionOrderInfoList.size() > 0) {
            responseInfo.setCode(2071);
            //状态为0没有配送单信息
            //map.put("returnState",0);
            map.put("employeeId", employee.getEmployeeId());//工号
            map.put("employeeName", employee.getEmployeeName());//姓名
            map.put("Mb", employee.getMb());//手机号
            map.put("distributionOrderInfoList", distributionOrderInfoList);//配送单信息集合
            responseInfo.setData(map);
        } else {
            responseInfo.setCode(1);
            //map.put("returnState",1);//有配送单信息
            map.put("employeeId", employee.getEmployeeId());//工号
            map.put("employeeName", employee.getEmployeeName());//姓名
            map.put("Mb", employee.getMb());//手机号
            map.put("distributionOrderInfoList", distributionOrderInfoList);//配送单信息集合
            responseInfo.setData(map);

        }
        return responseInfo;
    }
}
