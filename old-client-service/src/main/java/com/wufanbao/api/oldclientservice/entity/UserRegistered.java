package com.wufanbao.api.oldclientservice.entity;


import java.util.Date;

public class UserRegistered {
    private long userId;//userid
    private long parentUserId;//parentuserid
    private String userName;//用户名
    private String loginNo;//登录账号
    private String passWord;//登录密码
    private String payPassWord;//支付密码
    private int userType;//用户类别
    private String mB;//手机
    private String email;//邮箱
    private int integral;//积分
    private double balance;//账户余额
    private double usableBalance;//可用余额
    private double lockBalance;//锁定金额
    private String invitationCode;//邀请码
    private int isActive;//是否启用
    private Date createTime;//注册时间
    private int resetPasswordNeeded;//需要重设密码
    private int isAuth;//是否认证
    private int isCredit;//是否开启授信
    private int creditAmount;//授信总额
    private int creditUasbelAmount;//可用授信余额
    private int creditLimit;//授信规则
    private String portrait;//头像
    private String remark;//备注
    private long gradeValue;//经验值
    private boolean companyPayment;//是否企业付

    public boolean isCompanyPayment() {
        return companyPayment;
    }

    public void setCompanyPayment(boolean companyPayment) {
        this.companyPayment = companyPayment;
    }

    private int sex;//性别
    private Date birthday;//生日
    private Date breakFast;//早
    private Date lunch;//中
    private Date dinner;//晚

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Date getBreakFast() {
        return breakFast;
    }

    public void setBreakFast(Date breakFast) {
        this.breakFast = breakFast;
    }

    public Date getLunch() {
        return lunch;
    }

    public void setLunch(Date lunch) {
        this.lunch = lunch;
    }

    public Date getDinner() {
        return dinner;
    }

    public void setDinner(Date dinner) {
        this.dinner = dinner;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(long gradeValue) {
        this.gradeValue = gradeValue;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(long parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginNo() {
        return loginNo;
    }

    public void setLoginNo(String loginNo) {
        this.loginNo = loginNo;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPayPassWord() {
        return payPassWord;
    }

    public void setPayPassWord(String payPassWord) {
        this.payPassWord = payPassWord;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getmB() {
        return mB;
    }

    public void setmB(String mB) {
        this.mB = mB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getUsableBalance() {
        return usableBalance;
    }

    public void setUsableBalance(double usableBalance) {
        this.usableBalance = usableBalance;
    }

    public double getLockBalance() {
        return lockBalance;
    }

    public void setLockBalance(double lockBalance) {
        this.lockBalance = lockBalance;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getResetPasswordNeeded() {
        return resetPasswordNeeded;
    }

    public void setResetPasswordNeeded(int resetPasswordNeeded) {
        this.resetPasswordNeeded = resetPasswordNeeded;
    }

    public int getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(int isAuth) {
        this.isAuth = isAuth;
    }

    public int getIsCredit() {
        return isCredit;
    }

    public void setIsCredit(int isCredit) {
        this.isCredit = isCredit;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(int creditAmount) {
        this.creditAmount = creditAmount;
    }

    public int getCreditUasbelAmount() {
        return creditUasbelAmount;
    }

    public void setCreditUasbelAmount(int creditUasbelAmount) {
        this.creditUasbelAmount = creditUasbelAmount;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public static UserRegistered registered(long userId, String mb, String password, String userName, String portrait, int sex) {
        UserRegistered userRegistered = new UserRegistered();
        userRegistered.setUserId(userId);
        userRegistered.setParentUserId(0);
        userRegistered.setUserName(userName);
        userRegistered.setLoginNo(mb);
        userRegistered.setPassWord(password);
        userRegistered.setPayPassWord("");
        userRegistered.setUserType(1);
        userRegistered.setmB(mb);
        userRegistered.setEmail(" ");
        userRegistered.setIntegral(0);
        userRegistered.setBalance(0);
        userRegistered.setUsableBalance(0);
        userRegistered.setLockBalance(0);
        //根据id生成邀请码
        String invitationCode = Long.toHexString(userId);
        //邀请码
        userRegistered.setInvitationCode(invitationCode);
        userRegistered.setIsActive(1);
        userRegistered.setResetPasswordNeeded(0);
        userRegistered.setIsAuth(1);
        userRegistered.setIsCredit(0);
        userRegistered.setCreditAmount(0);
        userRegistered.setCreditUasbelAmount(0);
        userRegistered.setCreditLimit(0);
        userRegistered.setPortrait(portrait);
        userRegistered.setRemark(" ");
        userRegistered.setSex(sex);
        return userRegistered;
    }
}
