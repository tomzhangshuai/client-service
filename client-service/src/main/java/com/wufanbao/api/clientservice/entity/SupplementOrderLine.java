package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// SupplementOrderLine,补货单明细
public class SupplementOrderLine {
    //SupplementOrderLineId,
    private long supplementOrderLineId;
    //补货单ID,
    private long supplementOrderId;
    //机器编号,
    private long machineId;
    //商品id,
    private long productGlobalId;
    //占用下架商品,
    private long offProductGlobalId;
    //是否主食,
    private boolean isStaple;
    //库存更新时间,
    private Date stockUpdateTime;
    //库存数量,
    private int stockQuantity;
    //期望配送数量,
    private int expectQuantity;
    //预计库存消耗,
    private int stockConsume;
    //预计商品配送数量,
    private int quantity;
    //下货商品数量,
    private int dumpQuantity;
    //实际上架数量,
    private int actualQuantity;
    //是否锁定补货数量,
    private boolean lockQuantity;
    //机器异常数量,
    private int exceptionQuantity;
    //退回数量,
    private int backQuantity;

    public long getSupplementOrderLineId() {
        return this.supplementOrderLineId;
    }

    public void setSupplementOrderLineId(long supplementOrderLineId) {
        this.supplementOrderLineId = supplementOrderLineId;
    }

    public long getSupplementOrderId() {
        return this.supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
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

    public Date getStockUpdateTime() {
        return this.stockUpdateTime;
    }

    public void setStockUpdateTime(Date stockUpdateTime) {
        this.stockUpdateTime = stockUpdateTime;
    }

    public int getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getExpectQuantity() {
        return this.expectQuantity;
    }

    public void setExpectQuantity(int expectQuantity) {
        this.expectQuantity = expectQuantity;
    }

    public int getStockConsume() {
        return this.stockConsume;
    }

    public void setStockConsume(int stockConsume) {
        this.stockConsume = stockConsume;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDumpQuantity() {
        return this.dumpQuantity;
    }

    public void setDumpQuantity(int dumpQuantity) {
        this.dumpQuantity = dumpQuantity;
    }

    public int getActualQuantity() {
        return this.actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public boolean getLockQuantity() {
        return this.lockQuantity;
    }

    public void setLockQuantity(boolean lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public int getExceptionQuantity() {
        return this.exceptionQuantity;
    }

    public void setExceptionQuantity(int exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }

    public int getBackQuantity() {
        return this.backQuantity;
    }

    public void setBackQuantity(int backQuantity) {
        this.backQuantity = backQuantity;
    }

}
