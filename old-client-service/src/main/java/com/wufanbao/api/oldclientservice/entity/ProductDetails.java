package com.wufanbao.api.oldclientservice.entity;

/**
 * User:wangshihao
 * Date:2017-11-09
 * Time:10:04
 */
public class ProductDetails {
    private long productGlobalId;//商品id
    private String productName;//商品名称
    private String imageUrl;//主图地址
    private String description;//描述
    private String attachImageUrls;//幅图(多张图片)
    private double offlinePrice;//零售价(分)
    private double onlinePrice;//线上零售价(分)
    private int useableQuantity;//可用数量

    public int getUseableQuantity() {
        return useableQuantity;
    }

    public void setUseableQuantity(int useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachImageUrls() {
        return attachImageUrls;
    }

    public void setAttachImageUrls(String attachImageUrls) {
        this.attachImageUrls = attachImageUrls;
    }

    public double getOfflinePrice() {
        return offlinePrice;
    }

    public void setOfflinePrice(double offlinePrice) {
        this.offlinePrice = offlinePrice;
    }

    public double getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(double onlinePrice) {
        this.onlinePrice = onlinePrice;
    }
}
