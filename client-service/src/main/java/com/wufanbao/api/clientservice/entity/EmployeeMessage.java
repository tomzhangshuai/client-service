package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// EmployeeMessage,
public class EmployeeMessage {
    //MessageId,
    private long messageId;
    //接收人,
    private long receiveEmployeeId;
    //接受人姓名,
    private String receiveEmployeeName;
    //标题,
    private String title;
    //内容,
    private String content;
    //订单编号,
    private String orderNo;
    //链接地址,
    private String linkUrl;
    //消息类型,
    private MessageType messageType;
    //消息状态,
    private long messageStatus;
    //创建时间,
    private Date createTime;
    //修改时间,
    private Date updateTime;

    public long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getReceiveEmployeeId() {
        return this.receiveEmployeeId;
    }

    public void setReceiveEmployeeId(long receiveEmployeeId) {
        this.receiveEmployeeId = receiveEmployeeId;
    }

    public String getReceiveEmployeeName() {
        return this.receiveEmployeeName;
    }

    public void setReceiveEmployeeName(String receiveEmployeeName) {
        this.receiveEmployeeName = receiveEmployeeName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public MessageType getMessageType() {
        return this.messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public long getMessageStatus() {
        return this.messageStatus;
    }

    public void setMessageStatus(long messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
