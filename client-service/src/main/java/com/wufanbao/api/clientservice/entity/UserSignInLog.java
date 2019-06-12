package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserSignInLog,用户签到记录
public class UserSignInLog {
    //UserId,
    private long userId;
    //签到日期,
    private Date signedDay;
    //签到时间,
    private Date signedTime;
    //获得积分,
    private int integral;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getSignedDay() {
        return this.signedDay;
    }

    public void setSignedDay(Date signedDay) {
        this.signedDay = signedDay;
    }

    public Date getSignedTime() {
        return this.signedTime;
    }

    public void setSignedTime(Date signedTime) {
        this.signedTime = signedTime;
    }

    public int getIntegral() {
        return this.integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

}
