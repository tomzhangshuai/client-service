package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// CompanyFinancialFeedback,公司财务反馈
public class CompanyFinancialFeedback {
    //FinancialFeedbackId,
    private long financialFeedbackId;
    //CompanyId,
    private long companyId;
    //反馈人,
    private long feedbackEmployeeId;
    //问题日,
    private Date problemDate;
    //反馈时间,
    private Date feedbackTime;
    //反馈类型,
    private CompanyFinancialFeedbackType feedbackType;
    //描述,
    private String description;
    //凭证/证据,
    private String proof;
    //状态,
    private CompanyFinancialFeedbackStatus status;
    //是否解决,
    private boolean solve;
    //接收时间,
    private Date receiveTime;
    //开始处理时间,
    private Date handTime;
    //处理完成时间,
    private Date handedTime;
    //完结时间,
    private Date endTime;

    public long getFinancialFeedbackId() {
        return this.financialFeedbackId;
    }

    public void setFinancialFeedbackId(long financialFeedbackId) {
        this.financialFeedbackId = financialFeedbackId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getFeedbackEmployeeId() {
        return this.feedbackEmployeeId;
    }

    public void setFeedbackEmployeeId(long feedbackEmployeeId) {
        this.feedbackEmployeeId = feedbackEmployeeId;
    }

    public Date getProblemDate() {
        return this.problemDate;
    }

    public void setProblemDate(Date problemDate) {
        this.problemDate = problemDate;
    }

    public Date getFeedbackTime() {
        return this.feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public CompanyFinancialFeedbackType getFeedbackType() {
        return this.feedbackType;
    }

    public void setFeedbackType(CompanyFinancialFeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProof() {
        return this.proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public CompanyFinancialFeedbackStatus getStatus() {
        return this.status;
    }

    public void setStatus(CompanyFinancialFeedbackStatus status) {
        this.status = status;
    }

    public boolean getSolve() {
        return this.solve;
    }

    public void setSolve(boolean solve) {
        this.solve = solve;
    }

    public Date getReceiveTime() {
        return this.receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getHandTime() {
        return this.handTime;
    }

    public void setHandTime(Date handTime) {
        this.handTime = handTime;
    }

    public Date getHandedTime() {
        return this.handedTime;
    }

    public void setHandedTime(Date handedTime) {
        this.handedTime = handedTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
