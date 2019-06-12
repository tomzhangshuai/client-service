package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductLock,商品锁定，解锁表
public class ProductLock {
    //LockId,
    private long lockId;
    //ProductGlobalId,
    private long productGlobalId;
    //MachineId,
    private long machineId;
    //数量(+/-),
    private int quantity;
    //源类型,
    private String sourceType;
    //源id,
    private long sourceId;
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

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
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
