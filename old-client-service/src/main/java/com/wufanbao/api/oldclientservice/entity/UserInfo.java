package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;

public class UserInfo {
    private long userId;//用户ID
    private String loginNo;//账号
    private String password;//密码
    private String userName;//用户名
    private BigDecimal usableBalance;//便当币可用余额
    private long integral;//积分
    private String mb;//手机号
    private String portrait;//头像

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getUsableBalance() {
        return usableBalance;
    }

    public void setUsableBalance(BigDecimal usableBalance) {
        this.usableBalance = usableBalance;
    }

    public long getIntegral() {
        return integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
