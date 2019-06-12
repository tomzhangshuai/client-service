package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderInfo {
    private Date invalIdTime;
    private String description;
    private int status;
    private long userOrderId;
    private Date createTime;
    private BigDecimal amount;
    private BigDecimal actualAmount;
    private Date newDate;
    private Date payTime;
    private Date takeTime;
    private long discountId;
    private int payType;
    private String address;//机器地址
    private BigDecimal CouponAmount;//优惠金额
    private List<ProductOnline> productOnlineList;
    private long couponId;
    private long machineId;
    private BigDecimal discountAmount;//优惠
    private BigDecimal companyPayAmount;//企业付
    private BigDecimal familyPayAmount;//F家庭付
    private int machineType;

    public int getMachineType() {
        return machineType;
    }

    public void setMachineType(int machineType) {
        this.machineType = machineType;
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

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getCouponId() {
        return couponId;
    }

    public void setCouponId(long couponId) {
        this.couponId = couponId;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
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

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
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

    public List<ProductOnline> getProductOnlineList() {
        return productOnlineList;
    }

    public void setProductOnlineList(List<ProductOnline> productOnlineList) {
        this.productOnlineList = productOnlineList;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getNewDate() {
        return newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
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
}

