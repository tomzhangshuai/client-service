package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Area,地区表，位置分布-alpha
public class Area {
    //AreaId,
    private int areaId;
    //地区名称,
    private String areaName;
    //地区全称,
    private String areaFullName;
    //省,
    private int provinceId;
    //市,
    private int cityId;
    //区县,
    private int zoneId;
    //IsActive,
    private boolean isActive;
    //等级,
    private int level;

    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaFullName() {
        return this.areaFullName;
    }

    public void setAreaFullName(String areaFullName) {
        this.areaFullName = areaFullName;
    }

    public int getProvinceId() {
        return this.provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return this.cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getZoneId() {
        return this.zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
