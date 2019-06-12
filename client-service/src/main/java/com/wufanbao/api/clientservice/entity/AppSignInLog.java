package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AppSignInLog,app 用户登陆日志
public class AppSignInLog {
    //DeviceId,
    private long deviceId;
    //SignInTime,
    private Date signInTime;
    //UserId,
    private long userId;

    public long getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getSignInTime() {
        return this.signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
