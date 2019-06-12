package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.DistributionOrder;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;

public interface DistributionOperationService {
    /**
     * 出发
     *
     * @param distributionOrder 配送单信息
     * @return
     */
    ResponseInfo beganDelivery(DistributionOrder distributionOrder);

    /**
     * 终止
     *
     * @param distributionOrder 配送单信息
     * @return
     */
    ResponseInfo terminationDistribution(DistributionOrder distributionOrder);

    /**
     * 确认订单信息
     *
     * @param distributionOrder 配送单信息
     * @return
     */
    ResponseInfo confirmDeliveryOrder(DistributionOrder distributionOrder);

    /**
     * 确认订单
     *
     * @param distributionOrder
     * @return
     */
    ResponseInfo confirmAnOrder(DistributionOrder distributionOrder, String quantityGather);

    /**
     * 根据员工工号获取配送单信息
     *
     * @param employeeId
     * @return
     */
    ResponseInfo getDistributionOrderByEmployeeId(long employeeId);
}
