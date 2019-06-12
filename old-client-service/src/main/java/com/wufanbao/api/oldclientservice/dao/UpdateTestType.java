package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.Machine;
import org.apache.ibatis.annotations.Param;

public interface UpdateTestType {
    /**
     * 修改用户订单状态
     *
     * @param userOrderId
     * @param status
     * @return
     */
    int updateUserOrderType(@Param("userOrderId") long userOrderId, @Param("status") int status);

    /**
     * 修改补货单状态
     *
     * @param supplementOrderId
     * @param supplementStatus
     * @return
     */
    int updateSupplementOrderType(@Param("supplementOrderId") long supplementOrderId, @Param("supplementStatus") int supplementStatus);

    /**
     * 修改配送单状态
     *
     * @param distributionOrderId
     * @param distributionStatus
     * @return
     */
    int updateDistributionType(@Param("distributionOrderId") long distributionOrderId, @Param("distributionStatus") int distributionStatus);

    /**
     * 根据订单id去查询机器id
     *
     * @param userOrderId
     * @return
     */
    Machine selectMachineId(@Param("userOrderId") long userOrderId);
}
