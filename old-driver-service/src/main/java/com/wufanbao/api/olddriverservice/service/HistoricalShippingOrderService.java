package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.Employee;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;

public interface HistoricalShippingOrderService {
    /**
     * 查询历史订单
     *
     * @param employee 员工信息
     * @return
     */
    ResponseInfo getHistoricalShippingOrder(Employee employee);
}
