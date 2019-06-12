package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// PlatformPurchaseOrder,平台采购单  
public class PlatformPurchaseOrder {
    //PlatformPurchaseOrderId,
    private long platformPurchaseOrderId;
    //CreateTime,
    private Date createTime;
    //创建人,
    private long createrEmployeeId;
    //提交时间,
    private Date submitTime;
    //提交人,
    private long submiterEmployeeId;
    //期望交期,
    private Date expectReceive;
    //工厂交期,
    private Date factoryFeedback;
    //反馈时间,
    private Date feedback;
    //状态,
    private PlatformPurchaseOrderStatus status;

    public long getPlatformPurchaseOrderId() {
        return this.platformPurchaseOrderId;
    }

    public void setPlatformPurchaseOrderId(long platformPurchaseOrderId) {
        this.platformPurchaseOrderId = platformPurchaseOrderId;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreaterEmployeeId() {
        return this.createrEmployeeId;
    }

    public void setCreaterEmployeeId(long createrEmployeeId) {
        this.createrEmployeeId = createrEmployeeId;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public long getSubmiterEmployeeId() {
        return this.submiterEmployeeId;
    }

    public void setSubmiterEmployeeId(long submiterEmployeeId) {
        this.submiterEmployeeId = submiterEmployeeId;
    }

    public Date getExpectReceive() {
        return this.expectReceive;
    }

    public void setExpectReceive(Date expectReceive) {
        this.expectReceive = expectReceive;
    }

    public Date getFactoryFeedback() {
        return this.factoryFeedback;
    }

    public void setFactoryFeedback(Date factoryFeedback) {
        this.factoryFeedback = factoryFeedback;
    }

    public Date getFeedback() {
        return this.feedback;
    }

    public void setFeedback(Date feedback) {
        this.feedback = feedback;
    }

    public PlatformPurchaseOrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(PlatformPurchaseOrderStatus status) {
        this.status = status;
    }

}
