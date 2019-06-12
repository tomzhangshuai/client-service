package com.wufanbao.api.distributionservice.entities;

import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-09-07
 * Time:13:43
 * 补货单
 */
public class SupplementOrder {
    private long supplementOrderId;//SupplementOrderId
    private Date supplementday;//补货日期
    private long logisticCompanyId;//物流商
    private long joinCompanyId;//加盟商
    private long cityCompanyId;//城市运营商
    private long storeId;//仓库
    private long machineId;//机器编号
    private Date planTime;//计划配送时间
    private long orderEmployeeId;//下单确认操作人
    private Date orderTime;//下单时间
    private long auditorEmployeeId;//审核人
    private Date auditTime;//审核时间
    private long acceptEmployeeId;//接单操作人
    private Date acceptTime;//接单时间
    private String shelvingTime;//开始上架时间
    private String shelvedTime;//结束上架时间
    private int supplementStatus;//补货订单状态
    private Date cancelTime;//取消时间
    private String cencelReason;//取消原因

    public long getSupplementOrderId() {
        return supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public Date getSupplementday() {
        return supplementday;
    }

    public void setSupplementday(Date supplementday) {
        this.supplementday = supplementday;
    }

    public long getLogisticCompanyId() {
        return logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public long getJoinCompanyId() {
        return joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public long getCityCompanyId() {
        return cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public long getOrderEmployeeId() {
        return orderEmployeeId;
    }

    public void setOrderEmployeeId(long orderEmployeeId) {
        this.orderEmployeeId = orderEmployeeId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public long getAuditorEmployeeId() {
        return auditorEmployeeId;
    }

    public void setAuditorEmployeeId(long auditorEmployeeId) {
        this.auditorEmployeeId = auditorEmployeeId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public long getAcceptEmployeeId() {
        return acceptEmployeeId;
    }

    public void setAcceptEmployeeId(long acceptEmployeeId) {
        this.acceptEmployeeId = acceptEmployeeId;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getShelvingTime() {
        return shelvingTime;
    }

    public void setShelvingTime(String shelvingTime) {
        this.shelvingTime = shelvingTime;
    }

    public String getShelvedTime() {
        return shelvedTime;
    }

    public void setShelvedTime(String shelvedTime) {
        this.shelvedTime = shelvedTime;
    }

    public int getSupplementStatus() {
        return supplementStatus;
    }

    public void setSupplementStatus(int supplementStatus) {
        this.supplementStatus = supplementStatus;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCencelReason() {
        return cencelReason;
    }

    public void setCencelReason(String cencelReason) {
        this.cencelReason = cencelReason;
    }
}
