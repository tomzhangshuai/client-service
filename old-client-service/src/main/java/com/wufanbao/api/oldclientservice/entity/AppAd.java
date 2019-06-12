package com.wufanbao.api.oldclientservice.entity;

/**
 * User:WangZhiyuan
 */
public class AppAd {
    private long adId;//广告id
    private int asPosition;//广告位置
    private String adName;//广告名称
    private int adType;//广告跳转类型
    private String adImageUrl;//广告图片
    private String adResource;//广告跳转或请求资源
    private String androidNative;//安卓Native
    private String iosNative;//苹果Native
    private boolean isActive;//是否有效

    public String getAdImageUrl() {
        return adImageUrl;
    }

    public void setAdImageUrl(String adImageUrl) {
        this.adImageUrl = adImageUrl;
    }

    public long getAdId() {
        return adId;
    }

    public void setAdId(long adId) {
        this.adId = adId;
    }

    public int getAsPosition() {
        return asPosition;
    }

    public void setAsPosition(int asPosition) {
        this.asPosition = asPosition;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public String getAdResource() {
        return adResource;
    }

    public void setAdResource(String adResource) {
        this.adResource = adResource;
    }

    public String getAndroidNative() {
        return androidNative;
    }

    public void setAndroidNative(String androidNative) {
        this.androidNative = androidNative;
    }

    public String getIosNative() {
        return iosNative;
    }

    public void setIosNative(String iosNative) {
        this.iosNative = iosNative;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
