package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 入库单,alpha
public class StoreOrderIn {
    //StoreOrderInId,
    private long storeOrderInId;
    //企业,
    private long companyId;
    //仓库,
    private long storeId;
    //标题,
    private String title;
    //入库人,
    private long employeeId;
    //创建时间,
    private Date createTime;
    //备注,
    private String remark;
    //审核人,
    private long auditorEmployeeId;
    //审核时间,
    private Date auditTime;
    //仓管员,
    private long storeEmployeeId;
    //入库时间,
    private Date storageTime;
    //状态,
    private StoreInOutStatus status;
    //源id,
    private long sourceId;
    //源类型,
    private String sourceType;

    public long getStoreOrderInId() {
        return this.storeOrderInId;
    }

    public void setStoreOrderInId(long storeOrderInId) {
        this.storeOrderInId = storeOrderInId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public long getStoreEmployeeId() {
        return this.storeEmployeeId;
    }

    public void setStoreEmployeeId(long storeEmployeeId) {
        this.storeEmployeeId = storeEmployeeId;
    }

    public Date getStorageTime() {
        return this.storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public StoreInOutStatus getStatus() {
        return this.status;
    }

    public void setStatus(StoreInOutStatus status) {
        this.status = status;
    }

    public long getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

}
