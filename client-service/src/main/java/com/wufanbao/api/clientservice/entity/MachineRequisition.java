package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MachineRequisition,设备机器申请单
public class MachineRequisition {
    //申请单编号,
    private long requisitionId;
    //申请设备型号,
    private String model;
    //申请区域,
    private int areaId;
    //预投放具体地址,
    private String address;
    //申请公司,
    private long companyId;
    //申请理由,
    private String reason;
    //申请人,
    private long applyEmployeeId;
    //申请时间,
    private Date applyTime;
    //申请人联系电话,
    private String applyPhone;
    //申请状态,
    private RequisitionStatus status;
    //城市运营商确认人,
    private long confirmEmployeeId;
    //城市运营商确认时间,
    private Date confirmTime;
    //伍饭宝审核人,
    private long auditor;
    //伍饭宝审核时间,
    private Date auditingTime;
    //预付款打款凭证,
    private String advanceProof;
    //余款打款凭证,
    private String remainderProof;
    //发货时间,
    private Date deliveryTime;
    //收货人,
    private long receiverEmployeeId;
    //确认收货时间,
    private Date receiveTime;

    public long getRequisitionId() {
        return this.requisitionId;
    }

    public void setRequisitionId(long requisitionId) {
        this.requisitionId = requisitionId;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getApplyEmployeeId() {
        return this.applyEmployeeId;
    }

    public void setApplyEmployeeId(long applyEmployeeId) {
        this.applyEmployeeId = applyEmployeeId;
    }

    public Date getApplyTime() {
        return this.applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyPhone() {
        return this.applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public RequisitionStatus getStatus() {
        return this.status;
    }

    public void setStatus(RequisitionStatus status) {
        this.status = status;
    }

    public long getConfirmEmployeeId() {
        return this.confirmEmployeeId;
    }

    public void setConfirmEmployeeId(long confirmEmployeeId) {
        this.confirmEmployeeId = confirmEmployeeId;
    }

    public Date getConfirmTime() {
        return this.confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public long getAuditor() {
        return this.auditor;
    }

    public void setAuditor(long auditor) {
        this.auditor = auditor;
    }

    public Date getAuditingTime() {
        return this.auditingTime;
    }

    public void setAuditingTime(Date auditingTime) {
        this.auditingTime = auditingTime;
    }

    public String getAdvanceProof() {
        return this.advanceProof;
    }

    public void setAdvanceProof(String advanceProof) {
        this.advanceProof = advanceProof;
    }

    public String getRemainderProof() {
        return this.remainderProof;
    }

    public void setRemainderProof(String remainderProof) {
        this.remainderProof = remainderProof;
    }

    public Date getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public long getReceiverEmployeeId() {
        return this.receiverEmployeeId;
    }

    public void setReceiverEmployeeId(long receiverEmployeeId) {
        this.receiverEmployeeId = receiverEmployeeId;
    }

    public Date getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

}
