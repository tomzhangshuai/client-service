package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserCoupon,用户优惠券
public class UserCoupon {
    //主键,
    private long couponId;
    //优惠券定义,
    private long couponDefinitionId;
    //UserId,
    private long userId;
    //状态,
    private int status;
    //创建时间,
    private Date createTime;
    //开始时间,
    private Date startTime;
    //截至时间,
    private Date endTime;

    public long getCouponId() {
        return this.couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public long getCouponDefinitionId() {
        return this.couponDefinitionId;
    }

    public void setCouponDefinitionId(long couponDefinitionId) {
        this.couponDefinitionId = couponDefinitionId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
