package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// PermissionGroup,
public class PermissionGroup {
    //权限组代码,
    private String permissionGroupCode;
    //权限组名称,
    private String permissionGroupName;
    //顺序,
    private long turn;
    //企业类别,
    private CompanyType companyTypeId;

    public String getPermissionGroupCode() {
        return this.permissionGroupCode;
    }

    public void setPermissionGroupCode(String permissionGroupCode) {
        this.permissionGroupCode = permissionGroupCode;
    }

    public String getPermissionGroupName() {
        return this.permissionGroupName;
    }

    public void setPermissionGroupName(String permissionGroupName) {
        this.permissionGroupName = permissionGroupName;
    }

    public long getTurn() {
        return this.turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public CompanyType getCompanyTypeId() {
        return this.companyTypeId;
    }

    public void setCompanyTypeId(CompanyType companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

}
