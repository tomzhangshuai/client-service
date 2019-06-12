package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 充值记录,
public class CompanyRecharge {
    //RechargeId,
    private long rechargeId;
    //充值人,
    private long employeeId;
    //所属商家,
    private long companyId;
    //交易流水号,
    private String tradeNo;
    //充值方式,
    private PayType payType;
    //充值金额,
    private BigDecimal amount;
    //充值时间,
    private Date createTime;
    //平台流水号,
    private String bCTradeNo;
    //支付状态,充值状态
    private PayStatus payStatus;

    public long getRechargeId() {
        return this.rechargeId;
    }

    public void setRechargeId(long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public PayType getPayType() {
        return this.payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBCTradeNo() {
        return this.bCTradeNo;
    }

    public void setBCTradeNo(String bCTradeNo) {
        this.bCTradeNo = bCTradeNo;
    }

    public PayStatus getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

}
