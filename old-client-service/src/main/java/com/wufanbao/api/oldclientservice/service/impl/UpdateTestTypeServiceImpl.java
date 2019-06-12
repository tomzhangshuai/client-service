package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UpdateTestType;
import com.wufanbao.api.oldclientservice.entity.Machine;
import com.wufanbao.api.oldclientservice.service.UpdateTestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTestTypeServiceImpl implements UpdateTestTypeService {
    @Autowired
    private UpdateTestType updateTestType;

    /**
     * 修改用户订单状态
     *
     * @param userOrderId
     * @param status
     * @return
     */
    @Override
    public int updateUserOrderType(long userOrderId, int status) {
        int count = updateTestType.updateUserOrderType(userOrderId, status);
        return count;
    }

    /**
     * 修改补货单状态
     *
     * @param supplementOrderId
     * @param supplementStatus
     * @return
     */
    @Override
    public int updateSupplementOrderType(long supplementOrderId, int supplementStatus) {
        int count = updateTestType.updateSupplementOrderType(supplementOrderId, supplementStatus);
        return count;
    }

    /**
     * @param distributionOrderId
     * @param distributionStatus
     * @return
     */
    @Override
    public int updateDistributionType(long distributionOrderId, int distributionStatus) {
        int count = updateTestType.updateDistributionType(distributionOrderId, distributionStatus);
        return count;
    }

    /**
     * 获取机器id
     *
     * @param userOrderId
     * @return
     */
    @Override
    public Machine selectMachineId(long userOrderId) {
        return updateTestType.selectMachineId(userOrderId);
    }
}
