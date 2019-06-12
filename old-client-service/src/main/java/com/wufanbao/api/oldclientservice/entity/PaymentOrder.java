package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:10:55
 * 待支付详情
 */
public class PaymentOrder {
    private long userOrderId;//订单id
    private Integer quantity;//购买数量
    private Integer actualQuantity;//取餐数量
    private double price;//单价
    private String ProductName;//商品名称
    private int isStaple;
    private String imageUrl;//商品图片
    private Date createTime;//下单时间
    private UserCoupon userCoupon;//优惠券表
    private double amount;//优惠券价钱
    private long discountId;//优惠券id
    private String address;
    private BigDecimal discountAmount;//优惠
    private BigDecimal companyPayAmount;//企业付
    private BigDecimal familyPayAmount;

    public int getIsStaple() {
        return isStaple;
    }

    public void setIsStaple(int isStaple) {
        this.isStaple = isStaple;
    }

    public BigDecimal getFamilyPayAmount() {
        return familyPayAmount;
    }

    public void setFamilyPayAmount(BigDecimal familyPayAmount) {
        this.familyPayAmount = familyPayAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getCompanyPayAmount() {
        return companyPayAmount;
    }

    public void setCompanyPayAmount(BigDecimal companyPayAmount) {
        this.companyPayAmount = companyPayAmount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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


    public long getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(long userOrderId) {
        this.userOrderId = userOrderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(Integer actualQuantity) {
        this.actualQuantity = actualQuantity;
    }


    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public UserCoupon getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(UserCoupon userCoupon) {
        this.userCoupon = userCoupon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
