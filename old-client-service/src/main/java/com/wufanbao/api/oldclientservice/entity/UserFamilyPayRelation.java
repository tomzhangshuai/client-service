package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserFamilyPayRelation {
    private long masterUserId;
    private long userId;
    private int quotaType;
    private boolean limitQuota;
    private BigDecimal totalQuota;
    private BigDecimal consumeQuota;
    private BigDecimal totalAmount;
    private boolean disabled;
    private Date updateTime;
    private Date createTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getMasterUserId() {
        return masterUserId;
    }

    public void setMasterUserId(long masterUserId) {
        this.masterUserId = masterUserId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

    public boolean isLimitQuota() {
        return limitQuota;
    }

    public void setLimitQuota(boolean limitQuota) {
        this.limitQuota = limitQuota;
    }

    public BigDecimal getTotalQuota() {
        return totalQuota;
    }

    public void setTotalQuota(BigDecimal totalQuota) {
        this.totalQuota = totalQuota;
    }

    public BigDecimal getConsumeQuota() {

        return consumeQuota;
    }

    public void setConsumeQuota(BigDecimal consumeQuota) {
        this.consumeQuota = consumeQuota;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
