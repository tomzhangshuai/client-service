package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;

public class UserGradeLog {
    private long userGradeLogId;
    private long userId;
    private int gain;
    private int balance;
    private int sourceType;
    private long sourceId;
    private Date createTime;
    private String Description;

    public long getUserGradeLogId() {
        return userGradeLogId;
    }

    public void setUserGradeLogId(long userGradeLogId) {
        this.userGradeLogId = userGradeLogId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
