package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// MessageInfo,用户消息
public class MessageInfo {
    //MessageInfoId,
    private long messageInfoId;
    //消息类型,
    private MessageInfoType messageType;
    //消息内容类型,
    private MessageInfoContentType contentType;
    //消息内容,
    private String content;
    //创建时间,
    private Date createTime;
    //是否有效,
    private boolean isActive;

    public long getMessageInfoId() {
        return this.messageInfoId;
    }

    public void setMessageInfoId(long messageInfoId) {
        this.messageInfoId = messageInfoId;
    }

    public MessageInfoType getMessageType() {
        return this.messageType;
    }

    public void setMessageType(MessageInfoType messageType) {
        this.messageType = messageType;
    }

    public MessageInfoContentType getContentType() {
        return this.contentType;
    }

    public void setContentType(MessageInfoContentType contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
