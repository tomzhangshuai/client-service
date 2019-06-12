package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 部门,部门-alpha
public class Department {
    //DepartmentId,
    private long departmentId;
    //部门名称,
    private String departmentName;
    //公司,
    private long companyId;
    //负责人,
    private long employeeId;
    //是否启用,
    private boolean isActive;
    //创建时间,
    private Date createTime;
    //修改时间,
    private Date updateTime;
    //备注,
    private String remark;
    //上级部门,上级地区,0表示顶级
    private long parentDepartmentId;
    //顺序,
    private long turn;
    //LeftSeed,
    private long leftSeed;
    //RightSeed,
    private long rightSeed;
    //子节点数量,
    private long childCount;
    //子孙节点数量,
    private long childCountAll;
    //级别,
    private long level;

    public long getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getParentDepartmentId() {
        return this.parentDepartmentId;
    }

    public void setParentDepartmentId(long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public long getTurn() {
        return this.turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public long getLeftSeed() {
        return this.leftSeed;
    }

    public void setLeftSeed(long leftSeed) {
        this.leftSeed = leftSeed;
    }

    public long getRightSeed() {
        return this.rightSeed;
    }

    public void setRightSeed(long rightSeed) {
        this.rightSeed = rightSeed;
    }

    public long getChildCount() {
        return this.childCount;
    }

    public void setChildCount(long childCount) {
        this.childCount = childCount;
    }

    public long getChildCountAll() {
        return this.childCountAll;
    }

    public void setChildCountAll(long childCountAll) {
        this.childCountAll = childCountAll;
    }

    public long getLevel() {
        return this.level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

}
