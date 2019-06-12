package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// StatsMachineProduct,统计机器商品
public class StatsMachineProduct {
    //MachineId,
    private long machineId;
    //发生/产生日期,
    private Date occurDate;
    //JoinCompanyId,
    private long joinCompanyId;
    //统计时间,
    private Date statisticalTime;
    //期初库存,
    private int beginQuantity;
    //销售数量,
    private int saledQuantity;
    //已补数量,
    private int suppledQuantity;
    //锁定数量,
    private int lockQuantity;
    //待补空闲数量,
    private int freeQuantity;
    //异常数量,
    private int exceptionQuantity;
    //当前库存,
    private int endQuantity;

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Date getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }

    public long getJoinCompanyId() {
        return this.joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public Date getStatisticalTime() {
        return this.statisticalTime;
    }

    public void setStatisticalTime(Date statisticalTime) {
        this.statisticalTime = statisticalTime;
    }

    public int getBeginQuantity() {
        return this.beginQuantity;
    }

    public void setBeginQuantity(int beginQuantity) {
        this.beginQuantity = beginQuantity;
    }

    public int getSaledQuantity() {
        return this.saledQuantity;
    }

    public void setSaledQuantity(int saledQuantity) {
        this.saledQuantity = saledQuantity;
    }

    public int getSuppledQuantity() {
        return this.suppledQuantity;
    }

    public void setSuppledQuantity(int suppledQuantity) {
        this.suppledQuantity = suppledQuantity;
    }

    public int getLockQuantity() {
        return this.lockQuantity;
    }

    public void setLockQuantity(int lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public int getFreeQuantity() {
        return this.freeQuantity;
    }

    public void setFreeQuantity(int freeQuantity) {
        this.freeQuantity = freeQuantity;
    }

    public int getExceptionQuantity() {
        return this.exceptionQuantity;
    }

    public void setExceptionQuantity(int exceptionQuantity) {
        this.exceptionQuantity = exceptionQuantity;
    }

    public int getEndQuantity() {
        return this.endQuantity;
    }

    public void setEndQuantity(int endQuantity) {
        this.endQuantity = endQuantity;
    }

}
