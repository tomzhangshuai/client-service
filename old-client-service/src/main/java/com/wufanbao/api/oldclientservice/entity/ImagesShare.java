package com.wufanbao.api.oldclientservice.entity;

public class ImagesShare {
    private long imagesShareId;//分享图片id
    private String description;//内容
    private String showImage;//展示图片
    private String shareImage;//分享图片
    private boolean isActive;//是否启用
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getImagesShareId() {
        return imagesShareId;
    }

    public void setImagesShareId(long imagesShareId) {
        this.imagesShareId = imagesShareId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShowImage() {
        return showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
