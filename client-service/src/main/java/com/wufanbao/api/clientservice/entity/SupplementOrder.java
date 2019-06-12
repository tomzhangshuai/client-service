package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// SupplementOrder,补货单
public class SupplementOrder {
    //SupplementOrderId,
    private long supplementOrderId;
    //补货日期,
    private Date supplementday;
    //物流商,
    private long logisticCompanyId;
    //加盟商,
    private long joinCompanyId;
    //城市运营商,
    private long cityCompanyId;
    //仓库,
    private long storeId;
    //机器编号,
    private long machineId;
    //计划配送时间,
    private Date planTime;
    //下单确认操作人,
    private long orderEmployeeId;
    //下单时间,
    private Date orderTime;
    //审核人,
    private long auditorEmployeeId;
    //审核时间,
    private Date auditTime;
    //接单操作人,
    private long acceptEmployeeId;
    //接单时间,
    private Date acceptTime;
    //开始上架时间,
    private Date shelvingTime;
    //结束上架时间,
    private Date shelvedTime;
    //补货订单状态,
    private SupplementStatus supplementStatus;
    //取消时间,
    private Date cancelTime;
    //取消原因,
    private String cencelReason;

    public long getSupplementOrderId() {
        return this.supplementOrderId;
    }

    public void setSupplementOrderId(long supplementOrderId) {
        this.supplementOrderId = supplementOrderId;
    }

    public Date getSupplementday() {
        return this.supplementday;
    }

    public void setSupplementday(Date supplementday) {
        this.supplementday = supplementday;
    }

    public long getLogisticCompanyId() {
        return this.logisticCompanyId;
    }

    public void setLogisticCompanyId(long logisticCompanyId) {
        this.logisticCompanyId = logisticCompanyId;
    }

    public long getJoinCompanyId() {
        return this.joinCompanyId;
    }

    public void setJoinCompanyId(long joinCompanyId) {
        this.joinCompanyId = joinCompanyId;
    }

    public long getCityCompanyId() {
        return this.cityCompanyId;
    }

    public void setCityCompanyId(long cityCompanyId) {
        this.cityCompanyId = cityCompanyId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public Date getPlanTime() {
        return this.planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public long getOrderEmployeeId() {
        return this.orderEmployeeId;
    }

    public void setOrderEmployeeId(long orderEmployeeId) {
        this.orderEmployeeId = orderEmployeeId;
    }

    public Date getOrderTime() {
        return this.orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public long getAuditorEmployeeId() {
        return this.auditorEmployeeId;
    }

    public void setAuditorEmployeeId(long auditorEmployeeId) {
        this.auditorEmployeeId = auditorEmployeeId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public long getAcceptEmployeeId() {
        return this.acceptEmployeeId;
    }

    public void setAcceptEmployeeId(long acceptEmployeeId) {
        this.acceptEmployeeId = acceptEmployeeId;
    }

    public Date getAcceptTime() {
        return this.acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getShelvingTime() {
        return this.shelvingTime;
    }

    public void setShelvingTime(Date shelvingTime) {
        this.shelvingTime = shelvingTime;
    }

    public Date getShelvedTime() {
        return this.shelvedTime;
    }

    public void setShelvedTime(Date shelvedTime) {
        this.shelvedTime = shelvedTime;
    }

    public SupplementStatus getSupplementStatus() {
        return this.supplementStatus;
    }

    public void setSupplementStatus(SupplementStatus supplementStatus) {
        this.supplementStatus = supplementStatus;
    }

    public Date getCancelTime() {
        return this.cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCencelReason() {
        return this.cencelReason;
    }

    public void setCencelReason(String cencelReason) {
        this.cencelReason = cencelReason;
    }

}
