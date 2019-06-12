package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// CompanyPaymentDefinition,企业付定义
public class CompanyPaymentDefinition {
    //PaymentDefinitionId,
    private long paymentDefinitionId;
    //公司id,
    private long companyId;
    //限定规则,
    private String limitRule;
    //额度类型,
    private QuotaType quotaType;
    //额度(元),
    private int quota;

    public long getPaymentDefinitionId() {
        return this.paymentDefinitionId;
    }

    public void setPaymentDefinitionId(long paymentDefinitionId) {
        this.paymentDefinitionId = paymentDefinitionId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getLimitRule() {
        return this.limitRule;
    }

    public void setLimitRule(String limitRule) {
        this.limitRule = limitRule;
    }

    public QuotaType getQuotaType() {
        return this.quotaType;
    }

    public void setQuotaType(QuotaType quotaType) {
        this.quotaType = quotaType;
    }

    public int getQuota() {
        return this.quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

}
