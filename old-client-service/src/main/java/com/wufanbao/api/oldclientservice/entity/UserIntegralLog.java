package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserIntegralLog {
    private long integralLogId;//用户积分id
    private long userId;//用户id
    private BigDecimal quantity;//数量
    private String sourceType;//来源
    private long sourceTypeId;//来源id
    private Date createTime;//创建时间
    private String description;//描述

    public long getIntegralLogId() {
        return integralLogId;
    }

    public void setIntegralLogId(long integralLogId) {
        this.integralLogId = integralLogId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceTypeId() {
        return sourceTypeId;
    }

    public void setSourceTypeId(long sourceTypeId) {
        this.sourceTypeId = sourceTypeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
