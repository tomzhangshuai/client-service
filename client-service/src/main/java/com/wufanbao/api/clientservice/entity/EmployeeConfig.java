package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// EmployeeConfig,保存用户配置
public class EmployeeConfig {
    //EmployeeId,
    private long employeeId;
    //VarName,
    private String varName;
    //VarValue,
    private String varValue;

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getVarName() {
        return this.varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarValue() {
        return this.varValue;
    }

    public void setVarValue(String varValue) {
        this.varValue = varValue;
    }

}
