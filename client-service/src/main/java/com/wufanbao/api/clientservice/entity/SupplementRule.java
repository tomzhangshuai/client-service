package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// SupplementRule,物流 商补货规则定义表
public class SupplementRule {
    //SupplementRuleId,
    private long supplementRuleId;
    //物流商,
    private long logisticCompanyId;
    //频次（天）,
    private int autoDays;
    //最低库存,
    private int autoLimit;
    //自动生成时间,
    private Date autoCreateTime;
    //最后确认时间,
    private Date lastConfirmTime;
    //物流开始配送时间,
    private Date distributionTime;

    public long getSupplementRuleId() {
        return this.supplementRuleId;
    }

    public void setSupplementRuleId(long supplementRuleId) {
        this.supplementRuleId = supplementRuleId;
    }

    public long getLogisticCompanyId() {
        return this.logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public int getAutoDays() {
        return this.autoDays;
    }

    public void setAutoDays(int autoDays) {
        this.autoDays = autoDays;
    }

    public int getAutoLimit() {
        return this.autoLimit;
    }

    public void setAutoLimit(int autoLimit) {
        this.autoLimit = autoLimit;
    }

    public Date getAutoCreateTime() {
        return this.autoCreateTime;
    }

    public void setAutoCreateTime(Date autoCreateTime) {
        this.autoCreateTime = autoCreateTime;
    }

    public Date getLastConfirmTime() {
        return this.lastConfirmTime;
    }

    public void setLastConfirmTime(Date lastConfirmTime) {
        this.lastConfirmTime = lastConfirmTime;
    }

    public Date getDistributionTime() {
        return this.distributionTime;
    }

    public void setDistributionTime(Date distributionTime) {
        this.distributionTime = distributionTime;
    }

}
