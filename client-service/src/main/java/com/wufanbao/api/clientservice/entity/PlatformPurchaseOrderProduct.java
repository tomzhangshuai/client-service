package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// PlatformPurchaseOrderProduct,平台采购单商品
public class PlatformPurchaseOrderProduct {
    //PlatformPurchaseOrderId,
    private long platformPurchaseOrderId;
    //ProductGlobalId,
    private long productGlobalId;
    //Quantity,
    private int quantity;
    //Price,
    private int price;

    public long getPlatformPurchaseOrderId() {
        return this.platformPurchaseOrderId;
    }

    public void setPlatformPurchaseOrderId(long platformPurchaseOrderId) {
        this.platformPurchaseOrderId = platformPurchaseOrderId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
