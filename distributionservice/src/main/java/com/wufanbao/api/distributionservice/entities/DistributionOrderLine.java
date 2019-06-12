package com.wufanbao.api.distributionservice.entities;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:13:43
 * 配送单明细
 */
public class DistributionOrderLine {
    private long distributionOrderId;//DistributionOrderId
    private long supplementOrderId;//SupplementOrderId
    private int turn;//排序
    private String arriveTime;//卸货时间
    private String distributedTime;//配送结束时间
    private int isDistributed;//是否已配送
    private int isTerminate;//是否终止

    public int getIsTerminate() {
        return isTerminate;
    }

    public void setIsTerminate(int isTerminate) {
        this.isTerminate = isTerminate;
    }

    public long getDistributionOrderId() {
        return distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public long getSupplementOrderId() {
        return supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDistributedTime() {
        return distributedTime;
    }

    public void setDistributedTime(String distributedTime) {
        this.distributedTime = distributedTime;
    }

    public int getIsDistributed() {
        return isDistributed;
    }

    public void setIsDistributed(int isDistributed) {
        this.isDistributed = isDistributed;
    }
}
