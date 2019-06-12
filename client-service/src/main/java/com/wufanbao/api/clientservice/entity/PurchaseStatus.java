package com.wufanbao.api.clientservice.entity;

//PurchaseStatus
//
public enum PurchaseStatus {
    //已取消:0
    Canceled,
    //已创建:1
    Created,
    //已提交:2
    Submited,
    //已确认:3
    Confirm,
    //平台已接收:4
    PlatformReceived,
    //平台已提交:5
    PlatformSubmited,
    //工厂已反馈:6
    Feedback,
    //已生产:8
    Produced,
    //已支付:9
    Paid,
    //已发货:10
    Delivered,
    //待入库:11
    PendingStore,
    //入库中:12
    Storing,
    //已终结:13
    Final
}
