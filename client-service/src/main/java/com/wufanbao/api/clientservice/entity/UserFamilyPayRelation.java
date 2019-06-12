package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserFamilyPayRelation,用户家庭付关系表
public class UserFamilyPayRelation {
    //MasterUserId,
    private long masterUserId;
    //子用户,
    private long userId;
    //额度类型,
    private int quotaType;
    //是否限定额度,
    private boolean limitQuota;
    //总额度,
    private BigDecimal totalQuota;
    //已消耗额度,
    private BigDecimal consumeQuota;
    //总使用金额,
    private BigDecimal totalAmount;
    //是否不可用,
    private boolean disabled;

    public long getMasterUserId() {
        return this.masterUserId;
    }

    public void setMasterUserId(long masterUserId) {
        this.masterUserId = masterUserId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getQuotaType() {
        return this.quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

    public boolean getLimitQuota() {
        return this.limitQuota;
    }

    public void setLimitQuota(boolean limitQuota) {
        this.limitQuota = limitQuota;
    }

    public BigDecimal getTotalQuota() {
        return this.totalQuota;
    }

    public void setTotalQuota(BigDecimal totalQuota) {
        this.totalQuota = totalQuota;
    }

    public BigDecimal getConsumeQuota() {
        return this.consumeQuota;
    }

    public void setConsumeQuota(BigDecimal consumeQuota) {
        this.consumeQuota = consumeQuota;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean getDisabled() {
        return this.disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

}
