package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// ProductApply,商品申请
public class ProductApply {
    //ApplyId,
    private long applyId;
    //ProductGlobalId,
    private long productGlobalId;
    //JoinCompanyId,
    private long joinCompanyId;
    //城市运营商,
    private long cityCompanyId;
    //MachineId,
    private long machineId;
    //申请零售价(分),
    private int offlineRetailPrice;
    //申请线上零售价(分),
    private int onlineRetailPrice;
    //期望库存,
    private int stock;
    //下架商品(无填0),
    private long offProductGlobalId;
    //审核状态,
    private AuditState status;
    //申请人,
    private long applyEmployeeId;
    //申请时间,
    private Date applyTime;
    //审核人,
    private long auditEmployeeId;
    //审核时间,
    private Date auditTime;
    //备注,
    private String remark;

    public long getApplyId() {
        return this.applyId;
    }

    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }

    public long getProductGlobalId() {
        return this.productGlobalId;
    }

    public void setProductGlobalId(long productGlobalId) {
        this.productGlobalId = productGlobalId;
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

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public int getOfflineRetailPrice() {
        return this.offlineRetailPrice;
    }

    public void setOfflineRetailPrice(int offlineRetailPrice) {
        this.offlineRetailPrice = offlineRetailPrice;
    }

    public int getOnlineRetailPrice() {
        return this.onlineRetailPrice;
    }

    public void setOnlineRetailPrice(int onlineRetailPrice) {
        this.onlineRetailPrice = onlineRetailPrice;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public long getOffProductGlobalId() {
        return this.offProductGlobalId;
    }

    public void setOffProductGlobalId(long offProductGlobalId) {
        this.offProductGlobalId = offProductGlobalId;
    }

    public AuditState getStatus() {
        return this.status;
    }

    public void setStatus(AuditState status) {
        this.status = status;
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

    public long getAuditEmployeeId() {
        return this.auditEmployeeId;
    }

    public void setAuditEmployeeId(long auditEmployeeId) {
        this.auditEmployeeId = auditEmployeeId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
