package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserWithdrawal,用户提款
public class UserWithdrawal {
    //WithdrawalId,
    private long withdrawalId;
    //UserId,
    private long userId;
    //提现金额,
    private BigDecimal amount;

    public long getWithdrawalId() {
        return this.withdrawalId;
    }

    public void setWithdrawalId(long withdrawalId) {
        this.withdrawalId = withdrawalId;
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

}
