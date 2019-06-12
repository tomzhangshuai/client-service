package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserCouponInfo {
    private long couponDefinitionId;
    private int couponType;
    private String couponName;
    private String useRule;
    private String validityRule;
    private BigDecimal amount;
    private String content;
    private long couponId;
    private long userId;
    private int status;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private String dayDescribe;
    private String notice;
    private String matters;
    private String territorialLimitation;

    public String getTerritorialLimitation() {
        return territorialLimitation;
    }

    public void setTerritorialLimitation(String territorialLimitation) {
        this.territorialLimitation = territorialLimitation;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getMatters() {
        return matters;
    }

    public void setMatters(String matters) {
        this.matters = matters;
    }

    public String getDayDescribe() {
        return dayDescribe;
    }

    public void setDayDescribe(String dayDescribe) {
        this.dayDescribe = dayDescribe;
    }

    public long getCouponDefinitionId() {
        return couponDefinitionId;
    }

    public void setCouponDefinitionId(long couponDefinitionId) {
        this.couponDefinitionId = couponDefinitionId;
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

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}