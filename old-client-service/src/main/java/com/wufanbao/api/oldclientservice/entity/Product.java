package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-09-04
 * Time:14:21
 */
public class Product {
    private long productGlobalId;//ProductGlobalId
    private long machineId;//设备号
    private long companyId;//CompanyId
    private int offlinePrice;//零售价(分)
    private int onlinePrice;//线上零售价(分)
    private long employeeId;//修改人
    private int isActive;//是否启用(上下架)
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private int maxQuantity;//期望最大库存
    private int quantity;//库存数
    private int lockQuantity;//锁定数
    private int useableQuantity;//可用数
    private long tum;//排序号
    private String remark;//备注
    private long offProductGlobalId;//占用下架商品

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getOfflinePrice() {
        return offlinePrice;
    }

    public void setOfflinePrice(int offlinePrice) {
        this.offlinePrice = offlinePrice;
    }

    public int getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(int onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(int lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public int getUseableQuantity() {
        return useableQuantity;
    }

    public void setUseableQuantity(int useableQuantity) {
        this.useableQuantity = useableQuantity;
    }

    public long getTum() {
        return tum;
    }

    public void setTum(long tum) {
        this.tum = tum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getOffProductGlobalId() {
        return offProductGlobalId;
    }

    public void setOffProductGlobalId(long offProductGlobalId) {
        this.offProductGlobalId = offProductGlobalId;
    }
}
