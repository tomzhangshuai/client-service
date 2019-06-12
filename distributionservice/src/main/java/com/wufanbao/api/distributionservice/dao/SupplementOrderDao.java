package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.SupplementOrder;
import org.apache.ibatis.annotations.Param;

/**
 *操作补货单订单表
 */
public interface SupplementOrderDao {

    /**
     * 设置补货单状态
     *
     * @param supplementOrderId 补货单ID
     * @return
     */
    public int setSupplementStatus(
            @Param("supplementOrderId") long supplementOrderId,
            @Param("status") int status);


    /**
     * 单获取补货单数据
     * @param supplementOrderId 补货单ID
     * @return
     */
    public SupplementOrder getOrder(
            @Param("supplementOrderId") long supplementOrderId);


    /**
     * 是否存在
     * @param machineId  机器ID
     * @param supplementOrderId  补货单ID
     * @return
     */
    public int isExistSupplementOrder(
            @Param("machineId") long machineId,
            @Param("supplementOrderId") long supplementOrderId);

    /**
     * 获取补货单状态
     * @param supplementOrderId 补货单ID
     * @return
     */
    public int getOrderStatus(
            @Param("supplementOrderId") long supplementOrderId);
    /**
     * 获取机器ID
     * @param supplementOrderId
     * @return
     */
    public long getMachineId(
            @Param("supplementOrderId") long supplementOrderId);

    /**
     * 补货单结束时间
     * @param supplementOrderId
     * @return
     */
    int setSupplementCompletedTime(@Param("supplementOrderId") long supplementOrderId);

}
