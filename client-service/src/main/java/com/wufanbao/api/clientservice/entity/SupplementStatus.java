package com.wufanbao.api.clientservice.entity;

//补货单状态
//补货单状态
public enum SupplementStatus {
    //已创建:1
    Created,
    //已确认:2
    Confirmed,
    //已审核:3
    Audited,
    //已接单:4
    Received,
    //已装货:5
    Loaded,
    //已卸货:6
    Dumped,
    //上前架中:7
    FrontShelving,
    //上前架完成:8
    FrontShelved,
    //上后架中:9
    BehindShelving,
    //配送完毕:10
    Delivered,
    //已取消:0
    Canceled
}
