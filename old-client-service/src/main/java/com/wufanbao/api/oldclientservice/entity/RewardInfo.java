package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * User:WangZhiyuan
 * Data:2017/08/04
 */
public class RewardInfo {
    private BigDecimal rewardValue;
    private int noInfo;
    private int succeedNumber;
    private List<InvitationUserInfo> iUIList;

    public BigDecimal getRewardValue() {
        return rewardValue;
    }

    public void setRewardValue(BigDecimal rewardValue) {
        this.rewardValue = rewardValue;
    }

    public int getNoInfo() {
        return noInfo;
    }

    public void setNoInfo(int noInfo) {
        this.noInfo = noInfo;
    }

    public int getSucceedNumber() {
        return succeedNumber;
    }

    public void setSucceedNumber(int succeedNumber) {
        this.succeedNumber = succeedNumber;
    }

    public List<InvitationUserInfo> getiUIList() {
        return iUIList;
    }

    public void setiUIList(List<InvitationUserInfo> iUIList) {
        this.iUIList = iUIList;
    }
}
