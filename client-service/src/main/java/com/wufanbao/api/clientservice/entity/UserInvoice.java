package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserInvoice,用户发票
public class UserInvoice {
    //UserInvoiceId,
    private long userInvoiceId;
    //UserId,
    private long userId;
    //发票类型,
    private int invoiceType;
    //发票金额,
    private BigDecimal invoiceAmount;
    //发票内容,
    private String content;
    //发票抬头类型,
    private int buyerType;
    //发票抬头(购买方名称),
    private String buyerName;
    //购买方纳税人识别号,
    private String buyerTaxID;
    //购买方注册地址及注册联系电话,
    private String buyerAC;
    //购买方开户行及账号,
    private String buyerAccount;
    //备注,
    private String remark;
    //电子邮箱,
    private String email;
    //邮寄收件人,
    private String recipient;
    //邮寄联系电话,
    private String contactNumber;
    //邮寄区域,
    private int areaId;
    //邮寄区域名称,
    private String areaName;
    //邮寄详细地址,
    private String address;
    //发票状态,
    private InvoiceStatus invoiceStatus;
    //申请时间,
    private Date createTime;
    //开票时间,
    private Date madeTime;
    //发送时间,
    private Date sendTime;

    public long getUserInvoiceId() {
        return this.userInvoiceId;
    }

    public void setUserInvoiceId(long userInvoiceId) {
        this.userInvoiceId = userInvoiceId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BigDecimal getInvoiceAmount() {
        return this.invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBuyerType() {
        return this.buyerType;
    }

    public void setBuyerType(int buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerName() {
        return this.buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTaxID() {
        return this.buyerTaxID;
    }

    public void setBuyerTaxID(String buyerTaxID) {
        this.buyerTaxID = buyerTaxID;
    }

    public String getBuyerAC() {
        return this.buyerAC;
    }

    public void setBuyerAC(String buyerAC) {
        this.buyerAC = buyerAC;
    }

    public String getBuyerAccount() {
        return this.buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InvoiceStatus getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMadeTime() {
        return this.madeTime;
    }

    public void setMadeTime(Date madeTime) {
        this.madeTime = madeTime;
    }

    public Date getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

}
