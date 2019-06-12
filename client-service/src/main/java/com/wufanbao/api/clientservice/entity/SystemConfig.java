package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 系统参数,
public class SystemConfig {
    //变量名称,
    private String varName;
    //变量值,
    private String varValue;

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
