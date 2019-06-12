package com.wufanbao.api.distributionservice.transfer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 配送中的订单详情
 * User:Wangshihao
 * Date:2017-09-07
 * Time:14:46
 */
public class DistributingOrderDetail {

    private long distributionOrderId;//配送单id
    private BigDecimal x;//当前设备坐标x
    private BigDecimal y;//当前设备坐标y
    private String address;//当前设备投放地址
    private long supplementOrderId;//补货单ID
    /**
     * 1,Created,已创建
     * 2,Confirmed,已确认
     * 3,Audited,已审核
     * 4,Received,已接单
     * 5,Loaded,已装货
     * 6,Dumped,已卸货
     * 7,Shelving,上架中
     * 8,Delivered,配送完毕
     * 0,Canceled,已取消
     */
    private Integer supplementStatus;//状态
    private Date allowBeginTime;//开始配送时间
    private Date allowEndTime;//结束配送时间
    private int countQuantity;//总配货量
    private long machineId;//机器id
    private Date shelvedTime;//补货单结束时间
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getShelvedTime() {
        return shelvedTime;
    }

    public void setShelvedTime(Date shelvedTime) {
        this.shelvedTime = shelvedTime;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getDistributionOrderId() {
        return distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getSupplementOrderId() {
        return supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public Integer getSupplementStatus() {
        return supplementStatus;
    }

    public void setSupplementStatus(Integer supplementStatus) {
        this.supplementStatus = supplementStatus;
    }

    public Date getAllowBeginTime() {
        return allowBeginTime;
    }

    public void setAllowBeginTime(Date allowBeginTime) {
        this.allowBeginTime = allowBeginTime;
    }

    public Date getAllowEndTime() {
        return allowEndTime;
    }

    public void setAllowEndTime(Date allowEndTime) {
        this.allowEndTime = allowEndTime;
    }

    public int getCountQuantity() {
        return countQuantity;
    }

    public void setCountQuantity(int countQuantity) {
        this.countQuantity = countQuantity;
    }
}
