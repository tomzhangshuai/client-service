package com.wufanbao.api.oldclientservice.entity;

/**
 * User:wangshihao
 * Date:2017-12-18
 * Time:10:40
 */
public class AppDiscover {
    private long appDiscoverId;
    private int isActive;
    private String imageUrl;
    private String contentUrl;
    private String shareImageUrl;
    private String shareTitle;
    private String shareSubtitle;

    public long getAppDiscoverId() {
        return appDiscoverId;
    }

    public void setAppDiscoverId(long appDiscoverId) {
        this.appDiscoverId = appDiscoverId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareSubtitle() {
        return shareSubtitle;
    }

    public void setShareSubtitle(String shareSubtitle) {
        this.shareSubtitle = shareSubtitle;
    }
}
