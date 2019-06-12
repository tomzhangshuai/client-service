package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-24
 * Time:16:25
 */
public class UserOrderin {
    private Date invalIdTime;
    private String description;
    private int status;
    private long userOrderId;
    private Date createTime;
    private BigDecimal amount;
    private BigDecimal actualAmount;
    private long discountId;
    private String address;//机器地址
    private BigDecimal CouponAmount;//优惠金额
    private List<ProductOnline> productOnlineList;

    public List<ProductOnline> getProductOnlineList() {
        return productOnlineList;
    }

    public void setProductOnlineList(List<ProductOnline> productOnlineList) {
        this.productOnlineList = productOnlineList;
    }

    public Date getInvalIdTime() {
        return invalIdTime;
    }

    public void setInvalIdTime(Date invalIdTime) {
        this.invalIdTime = invalIdTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCouponAmount() {
        return CouponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        CouponAmount = couponAmount;
    }
}
