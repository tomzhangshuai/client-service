package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;

public class ProductOnline {
    private Integer quantity;//数量
    private BigDecimal price;//单价
    private long productGlobalId;//商品id
    private String productName;//商品名称
    private String remark;//备注
    private String image60;
    private String attachImageUrls;
    private int isStaple;

    public int getIsStaple() {
        return isStaple;
    }

    public void setIsStaple(int isStaple) {
        this.isStaple = isStaple;
    }

    public String getAttachImageUrls() {
        return attachImageUrls;
    }

    public void setAttachImageUrls(String attachImageUrls) {
        this.attachImageUrls = attachImageUrls;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getImage60() {
        return image60;
    }

    public void setImage60(String image60) {
        this.image60 = image60;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
