package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserSignIn,用户签到
public class UserSignIn {
    //UserId,
    private long userId;
    //签到时间,
    private Date signedTime;
    //当前连续签到天数,
    private int continuousSignedDays;
    //总签到天数,
    private int totalSignedDays;
    //最大连续签到天数,
    private int maxContinuousSignedDays;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getSignedTime() {
        return this.signedTime;
    }

    public void setSignedTime(Date signedTime) {
        this.signedTime = signedTime;
    }

    public int getContinuousSignedDays() {
        return this.continuousSignedDays;
    }

    public void setContinuousSignedDays(int continuousSignedDays) {
        this.continuousSignedDays = continuousSignedDays;
    }

    public int getTotalSignedDays() {
        return this.totalSignedDays;
    }

    public void setTotalSignedDays(int totalSignedDays) {
        this.totalSignedDays = totalSignedDays;
    }

    public int getMaxContinuousSignedDays() {
        return this.maxContinuousSignedDays;
    }

    public void setMaxContinuousSignedDays(int maxContinuousSignedDays) {
        this.maxContinuousSignedDays = maxContinuousSignedDays;
    }

}
