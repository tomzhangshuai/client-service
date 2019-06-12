package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserGradeLog,用户成长记录
public class UserGradeLog {
    //UserGradeLogId,
    private long userGradeLogId;
    //UserId,
    private long userId;
    //本次获得值,
    private int gain;
    //获得后成长值,
    private long balance;
    //源类型,
    private String sourceType;
    //源id,
    private long sourceId;
    //发生时间,
    private Date createTime;
    //描述,
    private String description;

    public long getUserGradeLogId() {
        return this.userGradeLogId;
    }

    public void setUserGradeLogId(long userGradeLogId) {
        this.userGradeLogId = userGradeLogId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getGain() {
        return this.gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public long getBalance() {
        return this.balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
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

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
