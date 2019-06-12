package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserQuota,用户额度
public class UserQuota {
    //EmployeeId,
    private long employeeId;
    //UserId,
    private long userId;
    //PaymentDefinitionId,
    private long paymentDefinitionId;
    //是否限定额度,
    private boolean limitQuota;
    //总额度,
    private BigDecimal totalQuota;
    //已消耗额度,
    private BigDecimal consumeQuota;
    //已使用企业付金额,
    private BigDecimal totalAmount;

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPaymentDefinitionId() {
        return this.paymentDefinitionId;
    }

    public void setPaymentDefinitionId(long paymentDefinitionId) {
        this.paymentDefinitionId = paymentDefinitionId;
    }

    public boolean getLimitQuota() {
        return this.limitQuota;
    }

    public void setLimitQuota(boolean limitQuota) {
        this.limitQuota = limitQuota;
    }

    public BigDecimal getTotalQuota() {
        return this.totalQuota;
    }

    public void setTotalQuota(BigDecimal totalQuota) {
        this.totalQuota = totalQuota;
    }

    public BigDecimal getConsumeQuota() {
        return this.consumeQuota;
    }

    public void setConsumeQuota(BigDecimal consumeQuota) {
        this.consumeQuota = consumeQuota;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
