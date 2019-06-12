package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Product,alpha
public class Product {
    //ProductGlobalId,
    private long productGlobalId;
    //商品名称,
    private String productName;
    //描述,
    private String description;
    //主图地址,
    private String imageUrl;
    //幅图(多张图片),
    private String attachImageUrls;
    //口味,
    private int tasteType;
    //设备号,
    private long machineId;
    //所属公司,
    private long companyId;
    //零售价(分),
    private int offlinePrice;
    //线上零售价(分),
    private int onlinePrice;
    //修改人,
    private long employeeId;
    //是否启用(上下架),
    private boolean isActive;
    //创建时间,
    private Date createTime;
    //修改时间,
    private Date updateTime;
    //期望最大库存,
    private int maxQuantity;
    //库存数,
    private int quantity;
    //锁定数,
    private int lockQuantity;
    //可用数,
    private int useableQuantity;
    //排序号,降序
    private long turn;
    //备注,
    private String remark;
    //占用下架商品,
    private long offProductGlobalId;
    //是否主食,
    private boolean isStaple;
    //关联商品,
    private long relProductGlobalId;

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getOfflinePrice() {
        return this.offlinePrice;
    }

    public void setOfflinePrice(int offlinePrice) {
        this.offlinePrice = offlinePrice;
    }

    public int getOnlinePrice() {
        return this.onlinePrice;
    }

    public void setOnlinePrice(int onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getMaxQuantity() {
        return this.maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLockQuantity() {
        return this.lockQuantity;
    }

    public void setLockQuantity(int lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public int getUseableQuantity() {
        return this.useableQuantity;
    }

    public void setUseableQuantity(int useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public long getTurn() {
        return this.turn;
    }

    public void setTurn(long turn) {
        this.turn = turn;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getOffProductGlobalId() {
        return this.offProductGlobalId;
    }

    public void setOffProductGlobalId(long offProductGlobalId) {
        this.offProductGlobalId = offProductGlobalId;
    }

    public boolean getIsStaple() {
        return this.isStaple;
    }

    public void setIsStaple(boolean isStaple) {
        this.isStaple = isStaple;
    }

    public long getRelProductGlobalId() {
        return this.relProductGlobalId;
    }

    public void setRelProductGlobalId(long relProductGlobalId) {
        this.relProductGlobalId = relProductGlobalId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAttachImageUrls() {
        return attachImageUrls;
    }

    public void setAttachImageUrls(String attachImageUrls) {
        this.attachImageUrls = attachImageUrls;
    }

    public int getTasteType() {
        return tasteType;
    }

    public void setTasteType(int tasteType) {
        this.tasteType = tasteType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isStaple() {
        return isStaple;
    }

    public void setStaple(boolean staple) {
        isStaple = staple;
    }
}
