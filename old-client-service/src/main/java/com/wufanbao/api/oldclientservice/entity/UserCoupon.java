package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:14:35
 */
public class UserCoupon {
    private long couponId;
    private long couponDefinitionId;
    private long userId;
    private int status;
    private Date createTime;
    private Date startTime;
    private Date endTime;

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getCouponDefinitionId() {
        return couponDefinitionId;
    }

    public void setCouponDefinitionId(long couponDefinitionId) {
        this.couponDefinitionId = couponDefinitionId;
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
