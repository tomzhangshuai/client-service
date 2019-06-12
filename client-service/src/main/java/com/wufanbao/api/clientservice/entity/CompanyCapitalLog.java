package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// CompanyCapitalLog,资金流水日志表
public class CompanyCapitalLog {
    //CapitalLogId,
    private long capitalLogId;
    //所属企业,
    private long companyId;
    //经办人,
    private long employeeId;
    //发生时间,
    private Date insertTime;
    //金额,
    private BigDecimal amount;
    //是否收入,
    private boolean isIn;
    //源,
    private String sourceType;
    //关联ID,
    private long sourceId;
    //资金流水类型,
    private CompanyCapitalLogType capitalLogType;
    //明细,
    private String detial;
    //余额,
    private BigDecimal balance;

    public long getCapitalLogId() {
        return this.capitalLogId;
    }

    public void setCapitalLogId(long capitalLogId) {
        this.capitalLogId = capitalLogId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean getIsIn() {
        return this.isIn;
    }

    public void setIsIn(boolean isIn) {
        this.isIn = isIn;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public CompanyCapitalLogType getCapitalLogType() {
        return this.capitalLogType;
    }

    public void setCapitalLogType(CompanyCapitalLogType capitalLogType) {
        this.capitalLogType = capitalLogType;
    }

    public String getDetial() {
        return this.detial;
    }

    public void setDetial(String detial) {
        this.detial = detial;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
