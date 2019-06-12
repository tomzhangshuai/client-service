package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User:WangZhiyuan
 * Date:2017-08-05
 */
public class UserCapitalLog {
    private long userCapitalLogId;//用户资金流水id
    private long userId;//用户id
    private double amount;//金额
    private Date createTime;//创建时间
    private String sourceType;//来源
    private long sourceId;//来源id
    private String description;//描述
    private double balance;//余额

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getUserCapitalLogId() {
        return userCapitalLogId;
    }

    public void setUserCapitalLogId(long userCapitalLogId) {
        this.userCapitalLogId = userCapitalLogId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
