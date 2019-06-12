package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserRecharge,用户充值
public class UserRecharge {
    //RechargeId,
    private long rechargeId;
    //UserId,
    private long userId;
    //充值金额,
    private BigDecimal amount;
    //平台流水号,
    private String bcTradeNo;
    //交易流水号,
    private String tradeNo;
    //充值方式,
    private int payType;
    //充值时间,
    private Date createTime;
    //支付状态,
    private int payStatus;
    //实际到账,
    private BigDecimal actualAmount;
    //到账时间,
    private Date receiveTime;
    //退款时间,
    private Date refundTime;

    public long getRechargeId() {
        return this.rechargeId;
    }

    public void setRechargeId(long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBcTradeNo() {
        return this.bcTradeNo;
    }

    public void setBcTradeNo(String bcTradeNo) {
        this.bcTradeNo = bcTradeNo;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getPayType() {
        return this.payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getActualAmount() {
        return this.actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Date getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getRefundTime() {
        return this.refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

}
