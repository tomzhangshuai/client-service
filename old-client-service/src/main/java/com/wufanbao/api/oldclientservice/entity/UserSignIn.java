package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;

public class UserSignIn {
    private long userId;//用户id
    private Date signedTime;//签到时间
    private int continuousSignedDays;//连续签到天数
    private int totalSignedDays;//总签到天数
    private int maxContinuousSignedDays;//最大连续签到天数
    private double ranking;//排名
    private boolean ifSignIn;

    public boolean isIfSignIn() {
        return ifSignIn;
    }

    public void setIfSignIn(boolean ifSignIn) {
        this.ifSignIn = ifSignIn;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(Date signedTime) {
        this.signedTime = signedTime;
    }

    public int getContinuousSignedDays() {
        return continuousSignedDays;
    }

    public void setContinuousSignedDays(int continuousSignedDays) {
        this.continuousSignedDays = continuousSignedDays;
    }

    public int getTotalSignedDays() {
        return totalSignedDays;
    }

    public void setTotalSignedDays(int totalSignedDays) {
        this.totalSignedDays = totalSignedDays;
    }

    public int getMaxContinuousSignedDays() {
        return maxContinuousSignedDays;
    }

    public void setMaxContinuousSignedDays(int maxContinuousSignedDays) {
        this.maxContinuousSignedDays = maxContinuousSignedDays;
    }

    public double getRanking() {
        return ranking;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }
}
