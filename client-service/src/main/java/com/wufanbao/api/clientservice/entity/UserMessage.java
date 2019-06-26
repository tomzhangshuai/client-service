package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserMessage,用户消息
public class UserMessage {
    //MessageInfoId,
    private long messageInfoId;
    //UserId,
    private long userId;
    //是否已读,
    private boolean isRead;
    //是否删除,
    private boolean isDeleted;

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
