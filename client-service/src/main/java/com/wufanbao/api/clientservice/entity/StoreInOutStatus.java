package com.wufanbao.api.clientservice.entity;

//StoreInOutStatus
//出入库状态
public enum StoreInOutStatus {
    //已创建:1
    Created,
    //待审核 :2
    PendingAudit,
    //已审核:3
    Audited,
    //完成操作:4
    Completed,
    //已取消:0
    Canceled
}
