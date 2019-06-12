package com.wufanbao.api.clientservice.entity;

//RequisitionStatus
//设备申请状态
public enum RequisitionStatus {
    //已申请:1
    Created,
    //已确认:2
    Confirmed,
    //已审核:3
    Audited,
    //已发货:4
    Delivering,
    //已接收:5
    Received,
    //已取消:0
    Canceled
}
