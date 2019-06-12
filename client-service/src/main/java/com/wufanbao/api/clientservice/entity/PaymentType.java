package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// PaymentType,支付方式
public class PaymentType {
    //PaymentTypeId,
    private long paymentTypeId;
    //PaymentTypeName,
    private String paymentTypeName;

    public long getPaymentTypeId() {
        return this.paymentTypeId;
    }

    public void setPaymentTypeId(long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentTypeName() {
        return this.paymentTypeName;
    }

    public void setPaymentTypeName(String paymentTypeName) {
        this.paymentTypeName = paymentTypeName;
    }

}
