package com.wufanbao.api.olddriverservice.entity;

import java.util.Date;

/**
 * creator:WangZhiyuan
 * createTime;2017/9/7 10:30
 */
public class DistributionOrderInfo {
    private long distributionOrderId;//DistributionOrderId
    private int cargoQuantity;//货物数量
    private String plateNo;//车牌号
    private int deviceNumber;//设备数
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private int status;//配送单状态
    private int isTermination;

    public int getIsTermination() {
        return isTermination;
    }

    public void setIsTermination(int isTermination) {
        this.isTermination = isTermination;
    }

    public long getDistributionOrderId() {
        return distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public int getCargoQuantity() {
        return cargoQuantity;
    }

    public void setCargoQuantity(int cargoQuantity) {
        this.cargoQuantity = cargoQuantity;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(int deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
