package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * user;WangZhiyuan
 */
public class CouponDefintionId {
    private long couponDefinitionId;//优惠券定义表id
    private long activityId;//关联活动Id;
    private int couponType;//优惠券类型
    private String couponName;//优惠券名称
    private String useRule;//使用规则
    private String validityRule;//有效规则
    private BigDecimal amount;//优惠值
    private String content;//显示内容
    private int got;//领取量
    private int used;//已使用
    private Date createTime;//创建时间
    private boolean isActive;//是否有效

    public long getCouponDefinitionId() {
        return couponDefinitionId;
    }

    public void setCouponDefinitionId(long couponDefinitionId) {
        this.couponDefinitionId = couponDefinitionId;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getUseRule() {
        return useRule;
    }

    public void setUseRule(String useRule) {
        this.useRule = useRule;
    }

    public String getValidityRule() {
        return validityRule;
    }

    public void setValidityRule(String validityRule) {
        this.validityRule = validityRule;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGot() {
        return got;
    }

    public void setGot(int got) {
        this.got = got;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
