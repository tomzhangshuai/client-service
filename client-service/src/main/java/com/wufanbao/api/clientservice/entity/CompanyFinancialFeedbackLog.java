package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// CompanyFinancialFeedbackLog,公司财务反馈记录
public class CompanyFinancialFeedbackLog {
    //FinancialFeedbackLogId,
    private long financialFeedbackLogId;
    //FinancialFeedbackId,
    private long financialFeedbackId;
    //是否平台答复,
    private boolean platform;
    //回复人,
    private long employeeId;
    //发生时间,
    private Date occurTime;
    //内容,
    private String content;
    //凭证/证据,
    private String proof;

    public long getFinancialFeedbackLogId() {
        return this.financialFeedbackLogId;
    }

    public void setFinancialFeedbackLogId(long financialFeedbackLogId) {
        this.financialFeedbackLogId = financialFeedbackLogId;
    }

    public long getFinancialFeedbackId() {
        return this.financialFeedbackId;
    }

    public void setFinancialFeedbackId(long financialFeedbackId) {
        this.financialFeedbackId = financialFeedbackId;
    }

    public boolean getPlatform() {
        return this.platform;
    }

    public void setPlatform(boolean platform) {
        this.platform = platform;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getOccurTime() {
        return this.occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProof() {
        return this.proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

}
