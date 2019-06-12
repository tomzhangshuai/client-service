package com.wufanbao.api.oldclientservice.entity;

import java.util.List;

public class UserParticularOrderInfo {
    private int type;
    private List<OrderInfo> orderInfoList;
    private String encryptedOrder;

    public String getEncryptedOrder() {
        return encryptedOrder;
    }

    public void setEncryptedOrder(String encryptedOrder) {
        this.encryptedOrder = encryptedOrder;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }

    public void setOrderInfoList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
}
