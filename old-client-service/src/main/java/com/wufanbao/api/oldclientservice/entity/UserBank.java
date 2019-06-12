package com.wufanbao.api.oldclientservice.entity;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:13:50
 */
public class UserBank {
    private long userBankId;//主键id
    private long userId;//userid
    private String bankName;//开户行
    private String fullName;//开户姓名
    private String phone;//开户手机号
    private String account;//账号(银行卡号)

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getUserBankId() {
        return userBankId;
    }

    public void setUserBankId(long userBankId) {
        this.userBankId = userBankId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
