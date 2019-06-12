package com.wufanbao.api.oldclientservice.entity;

public class CompanyPaymentDefinition {
    private long paymentDefinition; //规则定义表id
    private long companyId;//公司id
    private String limitRule;//限定规则
    private boolean quotaType;//额度类型
    private int quota;//额度

    public long getPaymentDefinition() {
        return paymentDefinition;
    }

    public void setPaymentDefinition(long paymentDefinition) {
        this.paymentDefinition = paymentDefinition;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getLimitRule() {
        return limitRule;
    }

    public void setLimitRule(String limitRule) {
        this.limitRule = limitRule;
    }

    public boolean isQuotaType() {
        return quotaType;
    }

    public void setQuotaType(boolean quotaType) {
        this.quotaType = quotaType;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }
}
