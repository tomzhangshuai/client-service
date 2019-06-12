package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 提现记录,公司提现记录
public class CompanyWithdrawal {
    //提现Id,
    private long withdrawalId;
    //提现人,
    private long employeeId;
    //所属商家,
    private long companyId;
    //提现金额,
    private BigDecimal amount;
    //提现账户类别,
    private AccountType accountType;
    //开户行,银行名称
    private String accountBank;
    //开户行户名,提现户名
    private String accountName;
    //开户行账号,提现账户
    private String accountNo;
    //提现时间,提现申请时间
    private Date createTime;
    //支付流水号,平台流水号
    private String tradeNo;
    //状态,
    private WithdrawalStatus status;
    //处理时间,提现通过时间
    private Date processTime;
    //到账时间,
    private Date arrivalTime;
    //获取/通知时间,
    private Date obtainTime;
    //备注,
    private String remark;

    public long getWithdrawalId() {
        return this.withdrawalId;
    }

    public void setWithdrawalId(long withdrawalId) {
        this.withdrawalId = withdrawalId;
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

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountBank() {
        return this.accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTradeNo() {
        return this.tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public WithdrawalStatus getStatus() {
        return this.status;
    }

    public void setStatus(WithdrawalStatus status) {
        this.status = status;
    }

    public Date getProcessTime() {
        return this.processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Date getArrivalTime() {
        return this.arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getObtainTime() {
        return this.obtainTime;
    }

    public void setObtainTime(Date obtainTime) {
        this.obtainTime = obtainTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
