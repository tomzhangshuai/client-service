package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// DistributionOrderLine,配送单明细
public class DistributionOrderLine {
    //DistributionOrderId,
    private long distributionOrderId;
    //SupplementOrderId,
    private long supplementOrderId;
    //排序,
    private int turn;
    //卸货时间,
    private Date arriveTime;
    //是否已配送,
    private boolean isDistributed;
    //配送结束时间,
    private Date distributedTime;
    //是否终止,
    private boolean isTerminate;

    public long getDistributionOrderId() {
        return this.distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public long getSupplementOrderId() {
        return this.supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public Date getArriveTime() {
        return this.arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public boolean getIsDistributed() {
        return this.isDistributed;
    }

    public void setIsDistributed(boolean isDistributed) {
        this.isDistributed = isDistributed;
    }

    public Date getDistributedTime() {
        return this.distributedTime;
    }

    public void setDistributedTime(Date distributedTime) {
        this.distributedTime = distributedTime;
    }

    public boolean getIsTerminate() {
        return this.isTerminate;
    }

    public void setIsTerminate(boolean isTerminate) {
        this.isTerminate = isTerminate;
    }

}
