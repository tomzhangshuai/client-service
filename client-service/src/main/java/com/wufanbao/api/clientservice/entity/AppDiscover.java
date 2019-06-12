package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// AppDiscover,APP发现
public class AppDiscover {
    //AppDiscoverId,
    private long appDiscoverId;
    //是否可用,
    private boolean isActive;
    //图片地址,
    private String imageUrl;
    //内容地址,
    private String contentUrl;
    //分享配图,
    private String shareImageUrl;
    //分享标题,
    private String shareTitle;
    //分享副标题,
    private String shareSubtitle;

    public long getAppDiscoverId() {
        return this.appDiscoverId;
    }

    public void setAppDiscoverId(long appDiscoverId) {
        this.appDiscoverId = appDiscoverId;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContentUrl() {
        return this.contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getShareImageUrl() {
        return this.shareImageUrl;
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public String getShareTitle() {
        return this.shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareSubtitle() {
        return this.shareSubtitle;
    }

    public void setShareSubtitle(String shareSubtitle) {
        this.shareSubtitle = shareSubtitle;
    }

}
