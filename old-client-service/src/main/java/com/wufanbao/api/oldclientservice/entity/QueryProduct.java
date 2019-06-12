package com.wufanbao.api.oldclientservice.entity;

/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:16:32
 */
public class QueryProduct {
    private int quantity;//库存数
    private int lockQuantity;//锁定数
    private int useableQuantity;//可用数量
    private long productGlobalId;//商品id
    private long machineId;//机器id
    private Integer buyQuantity;//购买数量

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

    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
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

}
