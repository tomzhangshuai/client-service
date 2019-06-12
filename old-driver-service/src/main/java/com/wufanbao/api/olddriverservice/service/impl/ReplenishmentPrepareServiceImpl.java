package com.wufanbao.api.olddriverservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.olddriverservice.DriverSetting;
import com.wufanbao.api.olddriverservice.Tool.IDGenerator;
import com.wufanbao.api.olddriverservice.dao.EmployeeDao;
import com.wufanbao.api.olddriverservice.dao.ReplenishmentPrepareDao;
import com.wufanbao.api.olddriverservice.entity.ProductInfo;
import com.wufanbao.api.olddriverservice.service.ReplenishmentPrepareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * @program: AlphaDriver
 * @description: 补货准备工作
 * @author: Wang Zhiyuan
 * @create: 2018-03-24 16:28
 **/
@Service
@Transactional
public class ReplenishmentPrepareServiceImpl implements ReplenishmentPrepareService {
    @Autowired
    private DriverSetting driverSetting;

    @Autowired
    private ReplenishmentPrepareDao replenishmentPrepareDao;
    @Autowired
    private EmployeeDao employeeDao;
    /**
     * 物流商 上海大熊配送有限公司
     */
    long logisticCompanyId = 70;
    /**
     * 加盟商 上海临时展会有限责任公司
     */
    long joinCompanyId = 71;
    /**
     * 运营商 上海运营美食公司
     */
    long cityCompanyId = 41;
    /**
     * 仓库ID  新国际仓
     */
    long storeId = 18;

    /**
     * 展示机器信息
     *
     * @param machineId 机器ID
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    @Override
    public List<Map> showMachineInfo(long machineId) {
        List<Map> mapList = replenishmentPrepareDao.getMachineInfo(machineId);
        for (int i = 0; i < mapList.size(); i++) {
            String imageUrl = mapList.get(i).get("imageUrl").toString();
            imageUrl = driverSetting.getResource() + imageUrl;
            mapList.get(i).put("imageUrl", imageUrl);
        }
        return mapList;
    }

    /**
     * 准备工作
     *
     * @param machineId
     * @param product
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    @Override
    public Map preparatoryWork(String machineId, String product, String loginNo) {
        Map map = new HashMap();
        if (machineId == null || product == null || loginNo == null) {
            map.put("code", "1004");
            map.put("message", "数据为空");
            return map;
        }
        //机器ID
        long machineIdL = Long.parseLong(machineId);

        List<ProductInfo> productInfoList = JSON.parseArray(product, ProductInfo.class);
        //补货单ID
        long supplementOrderId = IDGenerator.generateById("supplementOrderId", machineIdL);
        //配送单ID
        long distributionOrderId = IDGenerator.generateById("supplementOrderId", 20180324201401L);
        //配送员ID
        Long employeeId = employeeDao.getEmployeeIdByMb(loginNo);
        if (employeeId == null) {
            map.put("code", "1003");
            map.put("message", "此配送员不存在");
            return map;
        }
        //生成配送单 1
        boolean distributionOrder = distributionOrder(distributionOrderId, employeeId);
        //生成补货单
        boolean supplementOrder = supplementOrder(supplementOrderId, machineIdL, employeeId);
        //生成配送单详情
        boolean distributionOrderLine = distributionOrderLine(distributionOrderId, supplementOrderId);
        if (distributionOrder && supplementOrder && distributionOrderLine) {
            for (int i = 0; i < productInfoList.size(); i++) {
                ProductInfo productInfo = productInfoList.get(i);
                //商品ID
                long productGlobalId = productInfo.getProductGlobalId();
                //是否主食
                boolean isStaple = productInfo.isStaple();
                //预计配送数量
                int quantity = productInfo.getQuantity();
                int maxQuantity = productInfo.getMaxQuantity();
                if (quantity > 0) {
                    //配送商品详情表
                    boolean distributionProductLine = distributionProductLine(distributionOrderId, productGlobalId, quantity, quantity, quantity);
                    //补货商品详情表
                    boolean supplementOrderLine = supplementOrderLine(supplementOrderId, machineIdL, productGlobalId, maxQuantity, quantity, isStaple);
                    if (!distributionProductLine && !supplementOrderLine) {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        map.put("code", "1001");
                        map.put("message", "配送单详情表，补货单详情表");
                        return map;
                    }
                }
            }
            map.put("code", "1");
            map.put("message", "成功");
            return map;
        }
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        map.put("code", "1002");
        map.put("message", "配送单，补货单，配送单详情");
        return map;
    }

    /**
     * 补货单商品表
     *
     * @param supplementOrderId 补货单ID
     * @param machineId         机器ID
     * @param productGlobalId   商品ID
     * @param expectQuantity    期望配送数量
     * @param quantity          计划配送数量
     * @param isStaple          是否主食
     * @return boolean
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    public boolean supplementOrderLine(long supplementOrderId, long machineId, long productGlobalId, int expectQuantity, int quantity, boolean isStaple) {
        //补货单详情表ID
        long supplementOrderLineId = IDGenerator.generateById("supplementOrderId", supplementOrderId);
        //库存更新时间
        Date stockUpdateTime = new Date();
        //库存数量
        int stockQuantity = 0;
        //实际上架数量
        int actualQuantity = 0;
        //预计消耗库存
        int stockConsume = 0;
        //是否锁定补货数量
        int lockQuantity = 0;
        //占用下架商品
        int offProductGlobalId = 0;
        //异常数量
        int exceptionQuantity = 0;
        //退回数量
        int backQuantity = 0;
        //下货数量
        int dumpQuantity = 0;
        //生成补货单详情表
        int count = replenishmentPrepareDao.generateSupplementOrderLine(supplementOrderLineId, supplementOrderId, machineId, productGlobalId, stockUpdateTime, stockQuantity, expectQuantity, quantity, actualQuantity, stockConsume, lockQuantity, dumpQuantity, offProductGlobalId, exceptionQuantity, backQuantity, isStaple);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 配送单商品详情表
     *
     * @param distributionOrderId 配送单ID
     * @param productGlobalId     商品ID
     * @param quantity            数量
     * @param planQuantity        计划配送数量
     * @param surplusQuantity     剩余数量
     * @return boolean
     * @author Wang Zhiyuan
     * @date 2018/3/25
     */
    public boolean distributionProductLine(long distributionOrderId, long productGlobalId, int quantity, int planQuantity, int surplusQuantity) {
        //额外配发数量
        int extraQuantity = 0;
        //实际上架数量
        int actualQuantity = 0;
        //入库数量
        int storeQuantity = 0;
        //异常数量
        int exceptionQuantity = 0;
        int count = replenishmentPrepareDao.generateDistributionProductLine(distributionOrderId, productGlobalId, quantity, planQuantity, extraQuantity, actualQuantity, surplusQuantity, storeQuantity, exceptionQuantity);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 补货单表
     *
     * @param supplementOrderId 补货单ID
     * @param machineId         机器ID
     * @return boolean
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    public boolean supplementOrder(long supplementOrderId, long machineId, long employeeId) {
        //下单确认操作人
        long orderEmployeeId = employeeId;
        //下单时间
        Date orderTime = new Date();
        //审核人
        long auditorEmployeeId = employeeId;
        // 审核时间
        Date auditTime = new Date();
        //接单操作人
        long acceptEmployeeId = employeeId;
        //补货单状态
        int supplementStatus = 5;
        //计划配送时间
        Date planTime = new Date();
        //补货日期
        Date supplementDay = new Date();
        //生成补货单
        int count = replenishmentPrepareDao.generateSupplementOrder(supplementOrderId, logisticCompanyId, joinCompanyId, machineId, orderEmployeeId, orderTime, auditorEmployeeId, auditTime, acceptEmployeeId, supplementStatus, planTime, storeId, cityCompanyId, supplementDay);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 配送单表
     *
     * @param distributionOrderId 配送单ID
     * @return boolean
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    public boolean distributionOrder(long distributionOrderId, long employeeId) {
        //配送人
        long distributeEmployeeId = employeeId;
        //车辆信息
        long vehicleId = 20;
        //驾驶员
        long driverEmployeeId = 145;
        //制单人
        long markEmployeeId = employeeId;
        //计划配送时间
        Date planDeliverTime = new Date();
        //开始配送时间
        Date beginDistributeTime = new Date();
        //配送单状态
        int distributeStatus = 4;
        //创建时间
        Date createTime = new Date();
        //配送日期
        Date distributeDate = new Date();
        //是否异常终止
        int isTermination = 0;
        //生成配送单
        int count = replenishmentPrepareDao.generateDistributionOrder(distributionOrderId, logisticCompanyId, distributeEmployeeId, storeId, vehicleId, driverEmployeeId, markEmployeeId, planDeliverTime, beginDistributeTime, distributeStatus, cityCompanyId, createTime, distributeDate, isTermination);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 配送单详情表
     *
     * @param distributionOrderId 配送单ID
     * @param supplementOrderId   补货单ID
     * @return boolean
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    public boolean distributionOrderLine(long distributionOrderId, long supplementOrderId) {
        //排序
        long turn = 1;
        //卸货时间
        Date arriveTime = new Date();
        //是否已配送
        int isDistributed = 0;
        //是否已终止
        int isTerminate = 0;
        //生成配送单详情
        int count = replenishmentPrepareDao.generateDistributionOrderLine(distributionOrderId, supplementOrderId, turn, arriveTime, isDistributed, isTerminate);
        if (count > 0) {
            return true;
        }
        return false;
    }


}
