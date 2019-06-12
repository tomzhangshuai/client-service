package com.wufanbao.api.clientservice.entity;

//支付状态
//
public enum PayStatus {
    //待付款:1
    Unpay,
    //已付款:2
    Pay,
    //已退款:3
    Refund,
    //已取消:0
    Cancel
}
