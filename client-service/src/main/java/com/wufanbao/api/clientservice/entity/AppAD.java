package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AppAD,APP 广告
public class AppAD {
    //ADId,
    private long aDId;
    //广告位置,
    private int aDPosition;
    //广告图片,
    private String aDImageUrl;
    //广告名称,
    private String aDName;
    //广告跳转类型,
    private int aDType;
    //广告跳转或请求资源,
    private String aDResource;
    //安卓native,
    private String androidNative;
    //iOS native,
    private String iOSNative;
    //是否有效,
    private boolean isActive;

    public long getADId() {
        return this.aDId;
    }

    public void setADId(long aDId) {
        this.aDId = aDId;
    }

    public int getADPosition() {
        return this.aDPosition;
    }

    public void setADPosition(int aDPosition) {
        this.aDPosition = aDPosition;
    }

    public String getADImageUrl() {
        return this.aDImageUrl;
    }

    public void setADImageUrl(String aDImageUrl) {
        this.aDImageUrl = aDImageUrl;
    }

    public String getADName() {
        return this.aDName;
    }

    public void setADName(String aDName) {
        this.aDName = aDName;
    }

    public int getADType() {
        return this.aDType;
    }

    public void setADType(int aDType) {
        this.aDType = aDType;
    }

    public String getADResource() {
        return this.aDResource;
    }

    public void setADResource(String aDResource) {
        this.aDResource = aDResource;
    }

    public String getAndroidNative() {
        return this.androidNative;
    }

    public void setAndroidNative(String androidNative) {
        this.androidNative = androidNative;
    }

    public String getiOSNative() {
        return this.iOSNative;
    }

    public void setiOSNative(String iOSNative) {
        this.iOSNative = iOSNative;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
