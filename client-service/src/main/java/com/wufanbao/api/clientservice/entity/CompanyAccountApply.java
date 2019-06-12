package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// CompanyAccountApply,公司开户账户申请
public class CompanyAccountApply {
    //AccountApplyId,
    private long accountApplyId;
    //CompanyId,
    private long companyId;
    //开户行,
    private String accountBank;
    //开户账户名称,
    private String accountName;
    //开户账户卡号,
    private String accountNo;
    //营业执照,
    private String businessLicense;
    //身份证/其他有效证见正面,
    private String iDCardPositive;
    //身份证/其他有效证见正面反面,
    private String iDCardNegative;
    //状态,
    private AuditState status;
    //申请人,
    private long applyEmployeeId;
    //申请时间,
    private Date applyTime;
    //审核人,
    private long auditManagerId;
    //审核时间,
    private Date auditTime;
    //审核结果,
    private String auditResult;
    //失效时间,
    private Date invalidTime;

    public long getAccountApplyId() {
        return this.accountApplyId;
    }

    public void setAccountApplyId(long accountApplyId) {
        this.accountApplyId = accountApplyId;
    }

    public long getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public String getAccountBank() {
        return this.accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return this.accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBusinessLicense() {
        return this.businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getIDCardPositive() {
        return this.iDCardPositive;
    }

    public void setIDCardPositive(String iDCardPositive) {
        this.iDCardPositive = iDCardPositive;
    }

    public String getIDCardNegative() {
        return this.iDCardNegative;
    }

    public void setIDCardNegative(String iDCardNegative) {
        this.iDCardNegative = iDCardNegative;
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

    public long getAuditManagerId() {
        return this.auditManagerId;
    }

    public void setAuditManagerId(long auditManagerId) {
        this.auditManagerId = auditManagerId;
    }

    public Date getAuditTime() {
        return this.auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditResult() {
        return this.auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public Date getInvalidTime() {
        return this.invalidTime;
    }

    public void setInvalidTime(Date invalidTime) {
        this.invalidTime = invalidTime;
    }

}
