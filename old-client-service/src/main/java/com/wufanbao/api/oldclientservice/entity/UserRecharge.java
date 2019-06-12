package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:14:16
 */
public class UserRecharge {
    private long rechargeId;//RechargeId
    private long userId;//UserId
    private double amount;//充值金额
    private String bcTradeNo;//平台流水号
    private String tradeNo;//交易流水号
    private int payType;//充值方式
    private Date createTime;//充值时间
    private int payStatus;//支付状态
    private double actualAmount;//实际到账
    private Date receiveTime;//到账时间

    public long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBcTradeNo() {
        return bcTradeNo;
    }

    public void setBcTradeNo(String bcTradeNo) {
        this.bcTradeNo = bcTradeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public double getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(double actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
}
