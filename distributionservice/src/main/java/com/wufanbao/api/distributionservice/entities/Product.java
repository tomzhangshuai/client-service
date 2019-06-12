package com.wufanbao.api.distributionservice.entities;

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
    private Integer offlinePrice;//零售价(分)
    private Integer onlinePrice;//线上零售价(分)
    private long employeeId;//修改人
    private Integer isActive;//是否启用(上下架)
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    private Integer maxQuantity;//期望最大库存
    private Integer quantity;//库存数
    private Integer lockQuantity;//锁定数
    private Integer useableQuantity;//可用数
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

    public Integer getOfflinePrice() {
        return offlinePrice;
    }

    public void setOfflinePrice(Integer offlinePrice) {
        this.offlinePrice = offlinePrice;
    }

    public Integer getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(Integer onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
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

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Integer lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public Integer getUseableQuantity() {
        return useableQuantity;
    }

    public void setUseableQuantity(Integer useableQuantity) {
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
