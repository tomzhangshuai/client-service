package com.wufanbao.api.clientservice.entity;

import com.alipay.api.domain.Account;

//订单来源类型
public enum OriginateType {
    //APP余额支付：1
    Account,
    //APP微信:2
    AppWeixin,
    //App支付宝:3
    AppAlipay,
    //微信公众号支付:4
    Weixin,
    //支付宝扫一扫:5
    Alipay
}
