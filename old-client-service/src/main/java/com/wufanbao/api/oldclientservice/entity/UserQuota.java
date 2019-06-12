package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;

public class UserQuota {
    private long employeeId;//员工id
    private long userId;//用户id
    private long paymentDefinitionId;//规则定义表id
    private int quotaType;//是否限定额度
    private BigDecimal totalQuota;//总额度
    private BigDecimal consumeQuota;//已经消耗额度
    private BigDecimal totalAmount;//已使用企业付的金额
    private long companyId;//企业id
    private BigDecimal balance;//企业余额

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPaymentDefinitionId() {
        return paymentDefinitionId;
    }

    public void setPaymentDefinitionId(long paymentDefinitionId) {
        this.paymentDefinitionId = paymentDefinitionId;
    }


    public BigDecimal getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(BigDecimal totalQuota) {
        this.totalQuota = totalQuota;
    }

    public BigDecimal getConsumeQuota() {
        return consumeQuota;
    }

    public void setConsumeQuota(BigDecimal consumeQuota) {
        this.consumeQuota = consumeQuota;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
