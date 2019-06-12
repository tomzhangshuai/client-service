package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserOrderCapital,用户订单资金来源表
public class UserOrderCapital {
    //UserOrderId,
    private long userOrderId;
    //资金类型,
    private CapitalType capitalType;
    //源ID,
    private long sourceId;
    //源类型,
    private String sourceType;
    //付款金额,
    private BigDecimal amount;
    //实收金额（扣除手续费）,
    private BigDecimal actualAmount;
    //描述,
    private String description;
    //创建时间,
    private Date createTime;
    //是否退款,
    private boolean refund;
    //退款金额,
    private BigDecimal refundAmount;
    //退款时间,
    private Date refundTime;

    public long getUserOrderId() {
        return this.userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public CapitalType getCapitalType() {
        return this.capitalType;
    }

    public void setCapitalType(CapitalType capitalType) {
        this.capitalType = capitalType;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualAmount() {
        return this.actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getRefund() {
        return this.refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public BigDecimal getRefundAmount() {
        return this.refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundTime() {
        return this.refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

}
