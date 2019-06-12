package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;

// CouponDefinition,优惠券定义表
public class CouponDefinition {
    //CouponDefinitionId,
    private long couponDefinitionId;
    //关联活动ID,
    private long activityId;
    //优惠券类型,
    private int couponType;
    //名称,
    private String couponName;
    //使用规则,
    private String useRule;
    //有效规则,
    private String validityRule;
    //优惠值,
    private BigDecimal amount;
    //显示内容,
    private String content;
    //领取量,
    private int got;
    //已使用,
    private int used;
    //创建时间,
    private Date createTime;
    //是否有效,
    private boolean isActive;

    public long getCouponDefinitionId() {
        return this.couponDefinitionId;
    }

    public void setCouponDefinitionId(long couponDefinitionId) {
        this.couponDefinitionId = couponDefinitionId;
    }

    public long getActivityId() {
        return this.activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public int getCouponType() {
        return this.couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getUseRule() {
        return this.useRule;
    }

    public void setUseRule(String useRule) {
        this.useRule = useRule;
    }

    public String getValidityRule() {
        return this.validityRule;
    }

    public void setValidityRule(String validityRule) {
        this.validityRule = validityRule;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getGot() {
        return this.got;
    }

    public void setGot(int got) {
        this.got = got;
    }

    public int getUsed() {
        return this.used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
