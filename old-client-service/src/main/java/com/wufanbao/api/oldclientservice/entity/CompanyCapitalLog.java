package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyCapitalLog {
    private long capitalLogId;//记录id
    private long companyId;//公司id
    private long employeeId;//经办人id
    private Date insertTime;
    private BigDecimal amount;//企业付金额
    private int isIn;//是否收入
    private String sourceType;//来源
    private String sourceId;//源id
    private int capitalLogType;//资金流水类型
    private String detial;//描述
    private int quotaType;//类型

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }


    public long getCapitalLogId() {
        return capitalLogId;
    }

    public void setCapitalLogId(long capitalLogId) {
        this.capitalLogId = capitalLogId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getIsIn() {
        return isIn;
    }

    public void setIsIn(int isIn) {
        this.isIn = isIn;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getCapitalLogType() {
        return capitalLogType;
    }

    public void setCapitalLogType(int capitalLogType) {
        this.capitalLogType = capitalLogType;
    }

    public String getDetial() {
        return detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }
}
