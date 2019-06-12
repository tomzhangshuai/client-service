package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserReward,用户奖励
public class UserReward {
    //RewardId,
    private long rewardId;
    //UserId,
    private long userId;
    //奖励源类型,
    private RewardSoureType sourceType;
    //奖励源ID,
    private long sourceId;
    //发生时间,
    private Date createTime;
    //奖励值(钱、券、积分),
    private BigDecimal rewardValue;
    //奖励类型,
    private RewardType rewardType;
    //奖励描述,
    private String reward;

    public long getRewardId() {
        return this.rewardId;
    }

    public void setRewardId(long rewardId) {
        this.rewardId = rewardId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RewardSoureType getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(RewardSoureType sourceType) {
        this.sourceType = sourceType;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getRewardValue() {
        return this.rewardValue;
    }

    public void setRewardValue(BigDecimal rewardValue) {
        this.rewardValue = rewardValue;
    }

    public RewardType getRewardType() {
        return this.rewardType;
    }

    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }

    public String getReward() {
        return this.reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

}
