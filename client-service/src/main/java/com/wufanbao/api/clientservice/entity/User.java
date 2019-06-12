package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// User,alpha
public class User {
    //UserId,
    private long userId;
    //ParentUserId,
    private long parentUserId;
    //用户名,
    private String userName;
    //登录帐号,全局唯一，至少6-20位只允许英文和数字
    private String loginNo;
    //登录密码,,MD5
    private String password;
    //支付密码,
    private String payPassword;
    //用户类别,
    private int userType;
    //手机,
    private String mb;
    //邮箱,
    private String email;
    //积分,
    private long integral;
    //账户余额,
    private BigDecimal balance;
    //可用余额,
    private BigDecimal usableBalance;
    //锁定金额,
    private BigDecimal lockBalance;
    //邀请码,
    private String invitationCode;
    //是否启用,
    private boolean isActive;
    //创建时间,
    private Date createTime;
    //需要重设密码,
    private boolean resetPasswordNeeded;
    //是否认证,
    private boolean isAuth;
    //是否开启授信,
    private boolean isCredit;
    //授信总额,
    private BigDecimal creditAmount;
    //可用授信额度,
    private BigDecimal creditUasbelAmount;
    //授信规则,
    private String creditLimit;
    //头像,
    private String portrait;
    //备注,
    private String remark;
    //是否企业付用户,
    private boolean companyPayment;
    //会员成长值,
    private long gradeValue;
    //性别,
    private int sex;
    //生日,
    private Date birthday;
    //早餐,
    private Date breakfast;
    //中餐,
    private Date lunch;
    //晚餐,
    private Date dinner;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getParentUserId() {
        return this.parentUserId;
    }

    public void setParentUserId(long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginNo() {
        return this.loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return this.payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getMb() {
        return this.mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getIntegral() {
        return this.integral;
    }

    public void setIntegral(long integral) {
        this.integral = integral;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUsableBalance() {
        return this.usableBalance;
    }

    public void setUsableBalance(BigDecimal usableBalance) {
        this.usableBalance = usableBalance;
    }

    public BigDecimal getLockBalance() {
        return this.lockBalance;
    }

    public void setLockBalance(BigDecimal lockBalance) {
        this.lockBalance = lockBalance;
    }

    public String getInvitationCode() {
        return this.invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getResetPasswordNeeded() {
        return this.resetPasswordNeeded;
    }

    public void setResetPasswordNeeded(boolean resetPasswordNeeded) {
        this.resetPasswordNeeded = resetPasswordNeeded;
    }

    public boolean getIsAuth() {
        return this.isAuth;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public boolean getIsCredit() {
        return this.isCredit;
    }

    public void setIsCredit(boolean isCredit) {
        this.isCredit = isCredit;
    }

    public BigDecimal getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getCreditUasbelAmount() {
        return this.creditUasbelAmount;
    }

    public void setCreditUasbelAmount(BigDecimal creditUasbelAmount) {
        this.creditUasbelAmount = creditUasbelAmount;
    }

    public String getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean getCompanyPayment() {
        return this.companyPayment;
    }

    public void setCompanyPayment(boolean companyPayment) {
        this.companyPayment = companyPayment;
    }

    public long getGradeValue() {
        return this.gradeValue;
    }

    public void setGradeValue(long gradeValue) {
        this.gradeValue = gradeValue;
    }

    public int getSex() {
        return this.sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBreakfast() {
        return this.breakfast;
    }

    public void setBreakfast(Date breakfast) {
        this.breakfast = breakfast;
    }

    public Date getLunch() {
        return this.lunch;
    }

    public void setLunch(Date lunch) {
        this.lunch = lunch;
    }

    public Date getDinner() {
        return this.dinner;
    }

    public void setDinner(Date dinner) {
        this.dinner = dinner;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                '}';
    }
}
