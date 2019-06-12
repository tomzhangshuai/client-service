package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;
import java.util.List;

public class ProductInfo {
    private long productGlobalId;//商品id
    private String productName;//商品名称
    private String description;//商品描述
    private String barcode;//条形码
    private Boolean isHeating;//是否需要加热;
    private int heatingSecs;//加热时间
    private String imageUrl;//主图地址
    private String image60;//小图地址
    private String displayUnit;//显示单位
    private int tasteType;//口味
    private int useableQuantity;//可用数
    private boolean isActive;//是否上架中
    private double offLinePrice;//线下零售价
    private double onLinePrice;//线上零售价
    private long machineId;//机器编码
    private double distance;//距离
    private String address;//地址
    private long joinCompanyId;//加盟商订单
    private String attachImageUrls;//附图地址
    private List attachImageUrlList;
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List getAttachImageUrlList() {
        return attachImageUrlList;
    }

    public void setAttachImageUrlList(List attachImageUrlList) {
        this.attachImageUrlList = attachImageUrlList;
    }

    public String getAttachImageUrls() {
        return attachImageUrls;
    }

    public void setAttachImageUrls(String attachImageUrls) {
        this.attachImageUrls = attachImageUrls;
    }

    public long getJoinCompanyId() {
        return joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Boolean getHeating() {
        return isHeating;
    }

    public void setHeating(Boolean heating) {
        isHeating = heating;
    }

    public int getHeatingSecs() {
        return heatingSecs;
    }

    public void setHeatingSecs(int heatingSecs) {
        this.heatingSecs = heatingSecs;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage60() {
        return image60;
    }

    public void setImage60(String image60) {
        this.image60 = image60;
    }

    public String getDisplayUnit() {
        return displayUnit;
    }

    public void setDisplayUnit(String displayUnit) {
        this.displayUnit = displayUnit;
    }

    public int getTasteType() {
        return tasteType;
    }

    public void setTasteType(int tasteType) {
        this.tasteType = tasteType;
    }

    public int getUseableQuantity() {
        return useableQuantity;
    }

    public void setUseableQuantity(int useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public double getOffLinePrice() {
        return offLinePrice;
    }

    public void setOffLinePrice(double offLinePrice) {
        this.offLinePrice = offLinePrice;
    }

    public double getOnLinePrice() {
        return onLinePrice;
    }

    public void setOnLinePrice(double onLinePrice) {
        this.onLinePrice = onLinePrice;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
