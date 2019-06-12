package com.wufanbao.api.clientservice.entity;

//StoreInOutType
//库存出入库类型
public enum StoreInOutType {
    //采购入库:1
    Purchase,
    //补货出库:2
    Supplement,
    //初始化:3
    Init,
    //出库退回:4
    OutBack,
    //盘库抵消:5
    CheckNullify,
    //盘库新增:6
    CheckAdd,
    //清除:7
    Clear
}
