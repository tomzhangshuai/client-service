package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// 意见反馈,
public class FeedBack {
    //FeedBackId,
    private long feedBackId;
    //反馈内容,
    private String content;
    //联系方式,
    private String contactInfo;
    //提交人,
    private long employeeId;
    //反馈时间,
    private Date insertTime;
    //是否已处理,
    private boolean isProcessed;
    //处理时间,
    private Date processTime;
    //处理人,未处理时，为0
    private long managerId;

    public long getFeedBackId() {
        return this.feedBackId;
    }

    public void setFeedBackId(long feedBackId) {
        this.feedBackId = feedBackId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public boolean getIsProcessed() {
        return this.isProcessed;
    }

    public void setIsProcessed(boolean isProcessed) {
        this.isProcessed = isProcessed;
    }

    public Date getProcessTime() {
        return this.processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public long getManagerId() {
        return this.managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

}
