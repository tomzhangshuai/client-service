package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserInvoiceOrder,用户发票与订单关联关系
public class UserInvoiceOrder {
    //UserId,
    private long userId;
    //UserInvoiceId,
    private long userInvoiceId;
    //UserOrderId,
    private long userOrderId;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserInvoiceId() {
        return this.userInvoiceId;
    }

    public void setUserInvoiceId(long userInvoiceId) {
        this.userInvoiceId = userInvoiceId;
    }

    public long getUserOrderId() {
        return this.userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

}
