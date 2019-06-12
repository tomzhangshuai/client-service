package com.wufanbao.api.oldclientservice.service;


import com.wufanbao.api.oldclientservice.entity.Machine;
import org.apache.ibatis.annotations.Param;

public interface UpdateTestTypeService {
    /**
     * 修改用户订单状态
     *
     * @param userOrderId
     * @param status
     * @return
     */
    int updateUserOrderType(long userOrderId, int status);

    /**
     * 修改补货单状态
     *
     * @param supplementOrderId
     * @param supplementStatus
     * @return
     */
    int updateSupplementOrderType(long supplementOrderId, int supplementStatus);

    /**
     * @param distributionOrderId
     * @param distributionStatus
     * @return
     */
    int updateDistributionType(long distributionOrderId, int distributionStatus);

    /**
     * 获取机器id
     *
     * @param userOrderId
     * @return
     */
    Machine selectMachineId(long userOrderId);
}
