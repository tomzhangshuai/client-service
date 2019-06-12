package com.wufanbao.api.oldclientservice.service;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.dao.MyUserOrderDao;
import com.wufanbao.api.oldclientservice.entity.UserOrder;
import com.wufanbao.api.oldclientservice.entity.UserOrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyUserOrderService {
    @Autowired
    private MyUserOrderDao myUserOrderDao;

    //确认订单
    @Transactional
    public int inserUserOrder(UserOrder userOrder, String userOrderLineStrs, long couponDefinitionId, String appointTime) {
        List<UserOrderLine> userOrderLines = JSON.parseArray(userOrderLineStrs, UserOrderLine.class);

        long userOrderId = IDGenerator.generateById("userOrderId", userOrder.getUserId());//生成订单Id
        return 1;
    }

    //取消订单：返回取消、点击取消、支付超时取消
    @Transactional
    public int cancelOrder(long userOrderId, long userId, long couponId) {
        return 1;
    }

    @Transactional
    public int payOrder(long userOrderId, long userId) {
        return 1;
    }
}
