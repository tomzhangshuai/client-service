package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserBank,用户银行卡
public class UserBank {
    //UserBankId,
    private long userBankId;
    //UserId,
    private long userId;
    //开户行,
    private String bankName;
    //开户姓名,
    private String fullName;
    //开户手机号,
    private String phone;
    //账号,
    private String account;

    public long getUserBankId() {
        return this.userBankId;
    }

    public void setUserBankId(long userBankId) {
        this.userBankId = userBankId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
