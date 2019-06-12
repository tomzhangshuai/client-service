package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserCapitalLog,用户资金流水
public class UserCapitalLog {
    //UserCapitalLogId,
    private long userCapitalLogId;
    //UserId,
    private long userId;
    //金额,
    private BigDecimal amount;
    //创建时间,
    private Date createTime;
    //源,
    private String sourceType;
    //SourceId,
    private long sourceId;
    //描述,
    private String description;
    //余额,
    private BigDecimal balance;
    //资金池细节,
    private String detail;
    //是否已经同步资金池,
    private boolean isSync;

    public long getUserCapitalLogId() {
        return this.userCapitalLogId;
    }

    public void setUserCapitalLogId(long userCapitalLogId) {
        this.userCapitalLogId = userCapitalLogId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean getIsSync() {
        return this.isSync;
    }

    public void setIsSync(boolean isSync) {
        this.isSync = isSync;
    }

}
