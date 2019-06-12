package com.wufanbao.api.distributionservice.entities;

import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:13:43
 * 补货单明细
 */
public class SupplementOrderLine {
    private long supplementOrderLineId;//SupplementOrderLineId
    private long supplementOrderId;//补货单ID
    private long machineId;//机器编号
    private long productGlobalId;//商品id
    private Date stockUpdateTime;//库存更新时间
    private Integer stockQuantity;//库存数量
    private Integer expectQuantity;//期望配送数量
    private Integer quantity;//预计商品配送数量
    private Integer actualQuantity;//实际上架数量
    private Integer stockConsume;//预计库存消耗
    private boolean lockQuantity;//是否锁定补货数量
    private Integer dumpQuantity;//下货商品数量
    private long offProductGlobalId;//绑定的下架商品
    private Integer exceptionQuantity;//异常数量
    private Integer backQuantity;//退回数量
    private boolean isStaple;//是否是主食

    public long getOffProductGlobalId() {
        return offProductGlobalId;
    }

    public void setOffProductGlobalId(long offProductGlobalId) {
        this.offProductGlobalId = offProductGlobalId;
    }

    public boolean isStaple() {
        return isStaple;
    }

    public void setStaple(boolean staple) {
        isStaple = staple;
    }

    public Integer getExceptionQuantity() {
        return exceptionQuantity;
    }

    public void setExceptionQuantity(Integer exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }

    public Integer getBackQuantity() {
        return backQuantity;
    }

    public void setBackQuantity(Integer backQuantity) {
        this.backQuantity = backQuantity;
    }

    public long getSupplementOrderLineId() {
        return supplementOrderLineId;
    }

    public void setSupplementOrderLineId(long supplementOrderLineId) {
        this.supplementOrderLineId = supplementOrderLineId;
    }

    public long getSupplementOrderId() {
        return supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getProductGlobalId() {
        return productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
    }

    public Date getStockUpdateTime() {
        return stockUpdateTime;
    }

    public void setStockUpdateTime(Date stockUpdateTime) {
        this.stockUpdateTime = stockUpdateTime;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getExpectQuantity() {
        return expectQuantity;
    }

    public void setExpectQuantity(Integer expectQuantity) {
        this.expectQuantity = expectQuantity;
    }

    public Integer getStockConsume() {
        return stockConsume;
    }

    public void setStockConsume(Integer stockConsume) {
        this.stockConsume = stockConsume;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDumpQuantity() {
        return dumpQuantity;
    }

    public void setDumpQuantity(Integer dumpQuantity) {
        this.dumpQuantity = dumpQuantity;
    }

    public Integer getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(Integer actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public boolean getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(boolean lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    @Override
    public String toString() {
        return "SupplementOrderLine{" +
                "supplementOrderLineId=" + supplementOrderLineId +
                ", supplementOrderId=" + supplementOrderId +
                ", machineId=" + machineId +
                ", productGlobalId=" + productGlobalId +
                ", stockUpdateTime=" + stockUpdateTime +
                ", stockQuantity=" + stockQuantity +
                ", expectQuantity=" + expectQuantity +
                ", quantity=" + quantity +
                ", actualQuantity=" + actualQuantity +
                ", stockConsume=" + stockConsume +
                ", lockQuantity=" + lockQuantity +
                ", dumpQuantity=" + dumpQuantity +
                ", offProductGlobalId=" + offProductGlobalId +
                ", exceptionQuantity=" + exceptionQuantity +
                ", backQuantity=" + backQuantity +
                ", isStaple=" + isStaple +
                '}';
    }
}
