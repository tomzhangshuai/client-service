package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserInvoice {
    private long userInvoiceId;
    private long userId;
    private int invoiceType;
    private BigDecimal invoiceAmount;
    private String content;
    private int buyerType;
    private String buyerName;
    private String buyerTaxId;
    private String buyerAc;
    private String buyerAccount;
    private String remark;
    private String email;
    private String recipient;
    private String contactNumber;
    private int areaId;
    private String address;
    private int invoiceStatus;
    private Date createTime;
    private Date madeTime;
    private Date sendTime;
    private String areaName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public long getUserInvoiceId() {
        return userInvoiceId;
    }

    public void setUserInvoiceId(long userInvoiceId) {
        this.userInvoiceId = userInvoiceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBuyerType() {
        return buyerType;
    }

    public void setBuyerType(int buyerType) {
        this.buyerType = buyerType;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerTaxId() {
        return buyerTaxId;
    }

    public void setBuyerTaxId(String buyerTaxId) {
        this.buyerTaxId = buyerTaxId;
    }

    public String getBuyerAc() {
        return buyerAc;
    }

    public void setBuyerAc(String buyerAc) {
        this.buyerAc = buyerAc;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(int invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMadeTime() {
        return madeTime;
    }

    public void setMadeTime(Date madeTime) {
        this.madeTime = madeTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
