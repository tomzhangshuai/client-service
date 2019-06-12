package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// CompanyCredit,政企授信
public class CompanyCredit {
    //CompanyId,
    private long companyId;
    //授信类型,
    private int creditType;
    //额度(元),
    private int quota;
    //余额,
    private BigDecimal balance;
    //用途,
    private String purpose;
    //保证金(元),
    private int deposit;
    //授信周期(月),
    private int creditCycle;

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getCreditType() {
        return this.creditType;
    }

    public void setCreditType(int creditType) {
        this.creditType = creditType;
    }

    public int getQuota() {
        return this.quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getDeposit() {
        return this.deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getCreditCycle() {
        return this.creditCycle;
    }

    public void setCreditCycle(int creditCycle) {
        this.creditCycle = creditCycle;
    }

}
