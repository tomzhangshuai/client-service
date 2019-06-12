package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ImagesShare,用户图片分享定义表
public class ImagesShare {
    //ImagesShareId,
    private long imagesShareId;
    //Description,
    private String description;
    //ShowImage,
    private String showImage;
    //ShareImage,
    private String shareImage;
    //IsActive,
    private boolean isActive;
    //Url,
    private String url;

    public long getImagesShareId() {
        return this.imagesShareId;
    }

    public void setImagesShareId(long imagesShareId) {
        this.imagesShareId = imagesShareId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShowImage() {
        return this.showImage;
    }

    public void setShowImage(String showImage) {
        this.showImage = showImage;
    }

    public String getShareImage() {
        return this.shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
