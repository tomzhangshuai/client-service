package com.wufanbao.api.olddriverservice.entity;

/**
 * @program: AlphaDriver
 * @description: 商品信息
 * @author: Wang Zhiyuan
 * @create: 2018-03-24 22:05
 **/
public class ProductInfo {
    private long productGlobalId;
    private int quantity;
    private int maxQuantity;
    private boolean isStaple;

    public boolean isStaple() {
        return isStaple;
    }

    public void setStaple(boolean staple) {
        isStaple = staple;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
