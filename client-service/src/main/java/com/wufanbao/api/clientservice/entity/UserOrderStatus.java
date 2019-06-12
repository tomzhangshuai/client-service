package com.wufanbao.api.clientservice.entity;

//UserOrderStatus
//用户订单状态
public enum UserOrderStatus {
    //已退款:-3
    Refunded,
    //已失效:-2
    Invalid,
    //退款中:-1
    Refunding,
    //已取消:0
    Canceled,
    //已创建:1
    Created,
    //待支付:2
    StayPay,
    //已支付:3
    Paid,
    //制作中:4
    Making,
    //已领取:5
    Took,
    //待评价:6
    StayAssess,
    //已完成:7
    Completed
}
