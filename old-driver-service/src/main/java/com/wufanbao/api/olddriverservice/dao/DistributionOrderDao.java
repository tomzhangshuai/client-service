package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.ConfirmDeliveryOrderInfo;
import com.wufanbao.api.olddriverservice.entity.DistributionOrder;
import com.wufanbao.api.olddriverservice.entity.DistributionOrderInfo;
import com.wufanbao.api.olddriverservice.entity.SupplementOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * creator:WangZhiyuan
 * createTime;2017/9/7 10:30
 */
public interface DistributionOrderDao {
    /**
     * 获取配送单信息
     *
     * @param distributeEmployeeId 配送员id
     * @return
     */
    List<DistributionOrderInfo> getDistributionOrderByEmployeeId(@Param("distributeEmployeeId") long distributeEmployeeId);

    /**
     * 获取历史订单信息
     *
     * @param distributeEmployeeId
     * @return
     */
    List<DistributionOrderInfo> getHistoricalShippingOrder(@Param("distributeEmployeeId") long distributeEmployeeId);

    /**
     * 根据配送单id查看货物数量
     *
     * @param distributionOrderId 配送单id
     * @return
     */
    int getCargoQuantity(@Param("distributionOrderId") long distributionOrderId);

    /**
     * 根据配送单id查询机器数量
     *
     * @param distributionOrderId
     * @return
     */
    int getDeviceNumber(@Param("distributionOrderId") long distributionOrderId);

    /**
     * 修改配送单状态
     *
     * @param distributionOrderId 配送单id
     * @param status              配送单状态
     * @return
     */
    int updateDeliverStatus(@Param("distributionOrderId") long distributionOrderId, @Param("status") int status);

    /**
     * 出发
     */
    int depart(@Param("distributionOrderId") long distributionOrderId, @Param("status") int status);

    /**
     * 终止订单
     *
     * @param distributionOrderId
     * @return
     */
    int TerminationDeliver(@Param("distributionOrderId") long distributionOrderId, @Param("remark") String remark);

    /**
     * 终止配送单和补货单的关系
     *
     * @param distributionOrderId
     * @return
     */
    int TerminationLine(@Param("distributionOrderId") long distributionOrderId);

    /**
     * 确认配送单信息
     *
     * @param distributionOrderId 配送单id
     * @return
     */
    List<ConfirmDeliveryOrderInfo> confirmDeliveryOrder(@Param("distributionOrderId") long distributionOrderId);

    /**
     * 查询是否有未完成的订单
     *
     * @param employeeId 员工Id
     * @return
     */
    List<DistributionOrder> checkUnfinishedDelivery(@Param("employeeId") long employeeId);

    /**
     * 确认配送单
     *
     * @param distributionOrderId
     * @param status
     * @return
     */
    int confirmDeliverStatus(@Param("distributionOrderId") long distributionOrderId, @Param("status") int status);


    /**
     * 获取配送单 正在配送的补货单
     *
     * @param distributionOrderId
     * @return
     */
    List<SupplementOrder> getDistributingSupplementOrder(@Param("distributionOrderId") long distributionOrderId);
}
