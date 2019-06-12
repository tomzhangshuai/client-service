package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Employee,员工-alpha
public class Employee {
    //EmployeeId,
    private long employeeId;
    //部门,
    private long departmentId;
    //员工姓名,
    private String employeeName;
    //所属公司,
    private long companyId;
    //登录帐号,全局唯一，至少6-20位只允许英文和数字
    private String loginNo;
    //登录密码,,MD5
    private String password;
    //手机,
    private String mb;
    //邮箱,
    private String email;
    //备注,
    private String remark;
    //是否启用,
    private boolean isActive;
    //创建时间,
    private Date createTime;
    //修改时间,
    private Date updateTime;
    //权限时间,
    private Date permissionTime;
    //需要重设密码,
    private boolean resetPasswordNeeded;
    //激活码,
    private String activateCode;

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeeName() {
        return this.employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPermissionTime() {
        return this.permissionTime;
    }

    public void setPermissionTime(Date permissionTime) {
        this.permissionTime = permissionTime;
    }

    public boolean getResetPasswordNeeded() {
        return this.resetPasswordNeeded;
    }

    public void setResetPasswordNeeded(boolean resetPasswordNeeded) {
        this.resetPasswordNeeded = resetPasswordNeeded;
    }

    public String getActivateCode() {
        return this.activateCode;
    }

    public void setActivateCode(String activateCode) {
        this.activateCode = activateCode;
    }

}
