package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserRewardInfo {
    private String mb;//手机号
    private String portrait;//头像
    private String reward;//二十元
    private Date createTime;//创建时间
    private String describe;//描述
    private BigDecimal rewardValue;

    public BigDecimal getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(BigDecimal rewardValue) {
        this.rewardValue = rewardValue;
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

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
