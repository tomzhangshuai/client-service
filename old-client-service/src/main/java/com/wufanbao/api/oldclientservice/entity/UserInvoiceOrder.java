package com.wufanbao.api.oldclientservice.entity;

public class UserInvoiceOrder {
    private long userId;
    private long userInvoiceId;
    private long userOrderId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserInvoiceId() {
        return userInvoiceId;
    }

    public void setUserInvoiceId(long userInvoiceId) {
        this.userInvoiceId = userInvoiceId;
    }

    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }
}
