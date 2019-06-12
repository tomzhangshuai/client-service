package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.PaymentOrder;
import com.wufanbao.api.oldclientservice.entity.UserOrderLine;
import com.wufanbao.api.oldclientservice.entity.UserOrderin;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-10
 * Time:14:45
 */
public interface UserOrderLineService {

    /**
     * 订单详情
     */
    public List<PaymentOrder> queryOrderLine(long userOrderId);

    /**
     * 代付款订单
     */
    public List<UserOrderin> queryOrder(long userId);

    /**
     * 将商品加入用户订单明细表
     */
    int insertOrderLine(UserOrderLine userOrderLine);
}
