package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * User:wangshihao
 * Date:2017-12-12
 * Time:13:41
 */
public class UserOrderProductLine {
    private long userOrderProductLineId;
    private long userOrderId;//订单id
    private long productGlobalId;//商品id
    private long productOffId;//下架id
    private Timestamp relationTime;//关联时间
    private int status;//状态
    private BigDecimal price;
    private String productName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUserOrderProductLineId() {
        return userOrderProductLineId;
    }

    public void setUserOrderProductLineId(long userOrderProductLineId) {
        this.userOrderProductLineId = userOrderProductLineId;
    }

    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getProductOffId() {
        return productOffId;
    }

    public void setProductOffId(long productOffId) {
        this.productOffId = productOffId;
    }

    public Timestamp getRelationTime() {
        return relationTime;
    }

    public void setRelationTime(Timestamp relationTime) {
        this.relationTime = relationTime;
    }
}
