package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 盘存单,仓库盘存记录
public class StoreInventoryOrder {
    //盘存单ID,
    private long inventoryOrderId;
    //仓库,
    private long storeId;
    //企业,
    private long companyId;
    //盘存员,
    private long employeeId;
    //创建时间,
    private Date createTime;
    //盘存时间,
    private Date inventoryTime;
    //审核人,
    private long auditorEmpoyeeId;
    //审核时间,
    private Date auditTime;
    //状态,
    private StoreInOutStatus status;
    //备注,
    private String remark;

    public long getInventoryOrderId() {
        return this.inventoryOrderId;
    }

    public void setInventoryOrderId(long inventoryOrderId) {
        this.inventoryOrderId = inventoryOrderId;
    }

    public long getStoreId() {
        return this.storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
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

    public Date getInventoryTime() {
        return this.inventoryTime;
    }

    public void setInventoryTime(Date inventoryTime) {
        this.inventoryTime = inventoryTime;
    }

    public long getAuditorEmpoyeeId() {
        return this.auditorEmpoyeeId;
    }

    public void setAuditorEmpoyeeId(long auditorEmpoyeeId) {
        this.auditorEmpoyeeId = auditorEmpoyeeId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public StoreInOutStatus getStatus() {
        return this.status;
    }

    public void setStatus(StoreInOutStatus status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
