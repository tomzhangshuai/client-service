package com.wufanbao.api.clientservice.entity;

//订单类型
//
public enum OrderType {
    //期初库存:1
    StoreInitial,
    //销售订单:2
    SalesOrder,
    //采购订单:3
    PurchaseOrder,
    //入库单:4
    StoreOrderIn,
    //领料单:5
    StoreOrderOut,
    //盘存单:6
    InventoryOrder
}
