package com.wufanbao.api.distributionservice.entities;

import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:10:18
 * 配送单
 */
public class DistributionOrder {
    private long distributionOrderId;//DistributionOrderId
    private long cityCompanyId;//城市运营商
    private long logisticCompanyId;//物流商
    private Date distributeDate;//配送日期
    private long distributeEmployeeId;//配送人
    private long storeId;//发货仓库
    private long vehicleId;//车辆信息
    private long driverEmployeeId;//驾驶员
    private Date createTime;//创建时间
    private long markEmployeeId;//制单人
    private Date planDeliverTime;//计划配送开始时间
    private Date acceptTime;//接单时间
    private Date takeTime;//提货时间
    private Date beginDistributeTime;//开始配送时间
    private Date endDistributeTime;//配送结束时间
    /**
     * 配送状态
     * 0,Canceled,已取消
     * 1,Created,已创建
     * 2, Fixed,已确定
     * 3,Accepted,已接单
     * 4,took,已出库
     * 5,distributing,配送中
     * 6,distributed,已配送
     * 7,已完成
     */
    private int status;//状态
    private String remark;//备注
    private int isTermination;


    public int getIsTermination() {
        return isTermination;
    }

    public void setIsTermination(int isTermination) {
        this.isTermination = isTermination;
    }


    public Date getDistributeDate() {
        return distributeDate;
    }

    public void setDistributeDate(Date distributeDate) {
        this.distributeDate = distributeDate;
    }

    public long getCityCompanyId() {
        return cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getDistributionOrderId() {
        return distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public long getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public long getDistributeEmployeeId() {
        return distributeEmployeeId;
    }

    public void setDistributeEmployeeId(long distributeEmployeeId) {
        this.distributeEmployeeId = distributeEmployeeId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getDriverEmployeeId() {
        return driverEmployeeId;
    }

    public void setDriverEmployeeId(long driverEmployeeId) {
        this.driverEmployeeId = driverEmployeeId;
    }

    public long getMarkEmployeeId() {
        return markEmployeeId;
    }

    public void setMarkEmployeeId(long markEmployeeId) {
        this.markEmployeeId = markEmployeeId;
    }

    public Date getPlanDeliverTime() {
        return planDeliverTime;
    }

    public void setPlanDeliverTime(Date planDeliverTime) {
        this.planDeliverTime = planDeliverTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public Date getBeginDistributeTime() {
        return beginDistributeTime;
    }

    public void setBeginDistributeTime(Date beginDistributeTime) {
        this.beginDistributeTime = beginDistributeTime;
    }

    public Date getEndDistributeTime() {
        return endDistributeTime;
    }

    public void setEndDistributeTime(Date endDistributeTime) {
        this.endDistributeTime = endDistributeTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
