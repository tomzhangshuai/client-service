package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// EmployeePermission,员工拥有的权限
public class EmployeePermission {
    //EmployeeId,
    private long employeeId;
    //PermissionCode,
    private String permissionCode;

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

}
