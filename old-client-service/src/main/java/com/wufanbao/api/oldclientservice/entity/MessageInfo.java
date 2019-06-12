package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;

public class MessageInfo {
    private long messageInfoId;//消息表id
    private int messageType;//消息类型
    private int contentType;//消息内容类型
    private String content;//消息内容
    private Date createTime;//创建消息时间

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private boolean isActive;//是否有效

    public long getMessageInfoId() {
        return messageInfoId;
    }

    public void setMessageInfoId(long messageInfoId) {
        this.messageInfoId = messageInfoId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
