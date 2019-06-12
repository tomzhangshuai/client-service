package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineModule,机器模块
public class MachineModule {
    //模块编号,
    private String moduleNo;
    //模块名称,
    private String moduleName;
    //模块图片,
    private String imgUrl;
    //模块型号,
    private String model;
    //模块参数(json),
    private String params;
    //模块类型,
    private ModuleType moduleType;

    public String getModuleNo() {
        return this.moduleNo;
    }

    public void setModuleNo(String moduleNo) {
        this.moduleNo = moduleNo;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getParams() {
        return this.params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public ModuleType getModuleType() {
        return this.moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

}
