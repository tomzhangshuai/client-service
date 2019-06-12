package com.wufanbao.api.oldclientservice.entity;

public class UserMessage {
    private long messageInfoId;//用户消息表id
    private long userId;//用户id
    private boolean isRead;//是否已读
    private boolean isDeleted;//是否删除

    public long getMessageInfoId() {
        return messageInfoId;
    }

    public void setMessageInfoId(long messageInfoId) {
        this.messageInfoId = messageInfoId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
