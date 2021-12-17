package com.wufanbao.api.clientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRefund {
    private long orderRefundId;      // '退款Id'
    private long orderId;
    private long joinCompanyId;
    private long machineId;
    private long UserId;
    private int status;
    private Date submitTime;
    private BigDecimal planRefundAmount;  //'计划退款金额',
    private BigDecimal actualRefundAmount;    // '实际退款金额',
    private BigDecimal refundWxpayAmount; // '微信退款',
    private BigDecimal refundAlipayAmount; //'支付宝退款',
    private BigDecimal refundCompanyPayAmount;// '退企业付金额',
    private BigDecimal refundFamilyPayAmount;// '退亲密付金额',
    private BigDecimal refundDiscountAmount;
    private Date refundTime; // '退款时间，第三方支付返回的时间',
    private boolean useDiscount; // '是否使用优惠',
    private BigDecimal refundReceiveAmount;// '退实际到账金额'

    public long getOrderRefundId() {
        return orderRefundId;
    }

    public void setOrderRefundId(long orderRefundId) {
        this.orderRefundId = orderRefundId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getJoinCompanyId() {
        return joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public BigDecimal getPlanRefundAmount() {
        return planRefundAmount;
    }

    public void setPlanRefundAmount(BigDecimal planRefundAmount) {
        this.planRefundAmount = planRefundAmount;
    }

    public BigDecimal getActualRefundAmount() {
        return actualRefundAmount;
    }

    public void setActualRefundAmount(BigDecimal actualRefundAmount) {
        this.actualRefundAmount = actualRefundAmount;
    }

    public BigDecimal getRefundWxpayAmount() {
        return refundWxpayAmount;
    }

    public void setRefundWxpayAmount(BigDecimal refundWxpayAmount) {
        this.refundWxpayAmount = refundWxpayAmount;
    }

    public BigDecimal getRefundAlipayAmount() {
        return refundAlipayAmount;
    }

    public void setRefundAlipayAmount(BigDecimal refundAlipayAmount) {
        this.refundAlipayAmount = refundAlipayAmount;
    }

    public BigDecimal getRefundCompanyPayAmount() {
        return refundCompanyPayAmount;
    }

    public void setRefundCompanyPayAmount(BigDecimal refundCompanyPayAmount) {
        this.refundCompanyPayAmount = refundCompanyPayAmount;
    }

    public BigDecimal getRefundFamilyPayAmount() {
        return refundFamilyPayAmount;
    }

    public void setRefundFamilyPayAmount(BigDecimal refundFamilyPayAmount) {
        this.refundFamilyPayAmount = refundFamilyPayAmount;
    }

    public BigDecimal getRefundDiscountAmount() {
        return refundDiscountAmount;
    }

    public void setRefundDiscountAmount(BigDecimal refundDiscountAmount) {
        this.refundDiscountAmount = refundDiscountAmount;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public boolean isUseDiscount() {
        return useDiscount;
    }

    public void setUseDiscount(boolean useDiscount) {
        this.useDiscount = useDiscount;
    }

    public BigDecimal getRefundReceiveAmount() {
        return refundReceiveAmount;
    }

    public void setRefundReceiveAmount(BigDecimal refundReceiveAmount) {
        this.refundReceiveAmount = refundReceiveAmount;
    }
}
