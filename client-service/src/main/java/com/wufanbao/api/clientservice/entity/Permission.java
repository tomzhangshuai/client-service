package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Permission,权限
public class Permission {
    //权限代码,
    private String permissionCode;
    //权限名称,
    private String permissionName;
    //所属组,
    private String permissionGroupCode;
    //说明,
    private String description;
    //顺序,
    private long turn;

    public String getPermissionCode() {
        return this.permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return this.permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionGroupCode() {
        return this.permissionGroupCode;
    }

    public void setPermissionGroupCode(String permissionGroupCode) {
        this.permissionGroupCode = permissionGroupCode;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTurn() {
        return this.turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

}
