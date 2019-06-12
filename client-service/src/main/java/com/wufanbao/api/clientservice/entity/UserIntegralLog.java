package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserIntegralLog,用户积分日志
public class UserIntegralLog {
    //IntegralLogId,
    private long integralLogId;
    //UserId,
    private long userId;
    //数量,
    private BigDecimal quantity;
    //剩余积分,
    private BigDecimal balance;
    //源,
    private String sourceType;
    //SourceId,
    private long sourceId;
    //CreateTime,
    private Date createTime;
    //描述,
    private String description;

    public long getIntegralLogId() {
        return this.integralLogId;
    }

    public void setIntegralLogId(long integralLogId) {
        this.integralLogId = integralLogId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getQuantity() {
        return this.quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
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
