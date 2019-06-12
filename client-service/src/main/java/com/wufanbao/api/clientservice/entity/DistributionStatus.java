package com.wufanbao.api.clientservice.entity;

//DistributionStatus
//配送状态
public enum DistributionStatus {
    //已取消:0
    Canceled,
    //已创建:1
    Created,
    //已确定:2
    Fixed,
    //已接单:3
    Accepted,
    //已出库:4
    Took,
    //配送中:5
    Distributing,
    //已配送:6
    Distributed,
    //返仓确认:7
    Confirm,
    //入库中:8
    Storing,
    //已完成:9
    Completed
}
