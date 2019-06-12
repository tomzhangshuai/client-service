package com.wufanbao.api.olddriverservice.entity;

import java.util.Date;

/**
 * 员工表
 * Create by：zhaojing
 * Date：2018.07.13 14:17
 */
public class Employee {
    private long employeeId;//EmployeeId
    private long departmentId;//部门
    private String employeeName;//员工姓名
    private long companyId;//所属公司
    private String loginNo;//登录帐号
    private String passWord;//登录密码
    private String Mb;//手机
    private String email;//邮箱
    private String remark;//备注
    private int isActive;//是否启用
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private Date permissionTime;//权限时间
    private int resetPasswordNeeded;//需要重设密码
    private String activateCode;//激活码

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    public String getMb() {
        return Mb;
    }

    public void setMb(String mb) {
        Mb = mb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPermissionTime() {
        return permissionTime;
    }

    public void setPermissionTime(Date permissionTime) {
        this.permissionTime = permissionTime;
    }

    public int getResetPasswordNeeded() {
        return resetPasswordNeeded;
    }

    public void setResetPasswordNeeded(int resetPasswordNeeded) {
        this.resetPasswordNeeded = resetPasswordNeeded;
    }

    public String getActivateCode() {
        return activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }
}
