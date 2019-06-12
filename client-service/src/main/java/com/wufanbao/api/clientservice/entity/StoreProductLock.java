package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StoreProductLock,仓库商品锁定，解锁表
public class StoreProductLock {
    //LockId,
    private long lockId;
    //仓库,
    private long storeId;
    //商品,
    private long productGlobalId;
    //批号,
    private String lotNo;
    //数量(+/-),
    private int quantity;
    //源类型,
    private String sourceType;
    //源id,
    private String sourceIds;
    //发生时间,
    private Date occurTime;
    //备注,
    private String remark;

    public long getLockId() {
        return this.lockId;
    }

    public void setLockId(long lockId) {
        this.lockId = lockId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public String getLotNo() {
        return this.lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceIds() {
        return this.sourceIds;
    }

    public void setSourceIds(String sourceIds) {
        this.sourceIds = sourceIds;
    }

    public Date getOccurTime() {
        return this.occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
