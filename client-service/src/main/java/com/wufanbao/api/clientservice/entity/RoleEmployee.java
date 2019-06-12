package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// RoleEmployee,alpha
public class RoleEmployee {
    //RoleId,
    private long roleId;
    //EmployeeId,
    private long employeeId;

    public long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

}
