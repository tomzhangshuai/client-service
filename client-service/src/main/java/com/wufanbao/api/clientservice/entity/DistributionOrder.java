package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// DistributionOrder,配送单
public class DistributionOrder {
    //DistributionOrderId,
    private long distributionOrderId;
    //城市运营商,
    private long cityCompanyId;
    //物流商,
    private long logisticCompanyId;
    //配送日期,
    private Date distributeDate;
    //配送人,
    private long distributeEmployeeId;
    //发货仓库,
    private long storeId;
    //车辆信息,
    private long vehicleId;
    //驾驶员,
    private long driverEmployeeId;
    //创建时间,
    private Date createTime;
    //制单人,
    private long markEmployeeId;
    //计划配送开始时间,
    private Date planDeliverTime;
    //接单时间,
    private Date acceptTime;
    //提货时间,
    private Date takeTime;
    //开始配送时间,
    private Date beginDistributeTime;
    //配送结束时间,
    private Date endDistributeTime;
    //状态,
    private DistributionStatus status;
    //备注,
    private String remark;
    //是否配送异常终止,
    private boolean isTermination;
    //配送异常终止原因,
    private String terminationReson;
    //配送确认时间,
    private Date confirmTime;
    //完成时间,
    private Date completedTime;

    public long getDistributionOrderId() {
        return this.distributionOrderId;
    }

    public void setDistributionOrderId(long distributionOrderId) {
        this.distributionOrderId = distributionOrderId;
    }

    public long getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public long getLogisticCompanyId() {
        return this.logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public Date getDistributeDate() {
        return this.distributeDate;
    }

    public void setDistributeDate(Date distributeDate) {
        this.distributeDate = distributeDate;
    }

    public long getDistributeEmployeeId() {
        return this.distributeEmployeeId;
    }

    public void setDistributeEmployeeId(long distributeEmployeeId) {
        this.distributeEmployeeId = distributeEmployeeId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getVehicleId() {
        return this.vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getDriverEmployeeId() {
        return this.driverEmployeeId;
    }

    public void setDriverEmployeeId(long driverEmployeeId) {
        this.driverEmployeeId = driverEmployeeId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getMarkEmployeeId() {
        return this.markEmployeeId;
    }

    public void setMarkEmployeeId(long markEmployeeId) {
        this.markEmployeeId = markEmployeeId;
    }

    public Date getPlanDeliverTime() {
        return this.planDeliverTime;
    }

    public void setPlanDeliverTime(Date planDeliverTime) {
        this.planDeliverTime = planDeliverTime;
    }

    public Date getAcceptTime() {
        return this.acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getTakeTime() {
        return this.takeTime;
    }

    public void setTakeTime(Date takeTime) {
        this.takeTime = takeTime;
    }

    public Date getBeginDistributeTime() {
        return this.beginDistributeTime;
    }

    public void setBeginDistributeTime(Date beginDistributeTime) {
        this.beginDistributeTime = beginDistributeTime;
    }

    public Date getEndDistributeTime() {
        return this.endDistributeTime;
    }

    public void setEndDistributeTime(Date endDistributeTime) {
        this.endDistributeTime = endDistributeTime;
    }

    public DistributionStatus getStatus() {
        return this.status;
    }

    public void setStatus(DistributionStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean getIsTermination() {
        return this.isTermination;
    }

    public void setIsTermination(boolean isTermination) {
        this.isTermination = isTermination;
    }

    public String getTerminationReson() {
        return this.terminationReson;
    }

    public void setTerminationReson(String terminationReson) {
        this.terminationReson = terminationReson;
    }

    public Date getConfirmTime() {
        return this.confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getCompletedTime() {
        return this.completedTime;
    }

    public void setCompletedTime(Date completedTime) {
        this.completedTime = completedTime;
    }

}
