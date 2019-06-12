package com.wufanbao.api.clientservice.entity;

//CompanySettleStatus
//公司结算状态
public enum CompanySettleStatus {
    //无:0
    None,
    //已创建:1
    Created,
    //修改中:2
    Modifing,
    //待审核:3
    PendingAudit,
    //已审核:4
    Audited,
    //已结算:5
    Settled
}
