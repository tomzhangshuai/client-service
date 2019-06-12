package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserLeaveMessage,用户留言
public class UserLeaveMessage {
    //LeaveMessageId,
    private long leaveMessageId;
    //UserId,
    private long userId;
    //LeaveMessage,
    private String leaveMessage;

    public long getLeaveMessageId() {
        return this.leaveMessageId;
    }

    public void setLeaveMessageId(long leaveMessageId) {
        this.leaveMessageId = leaveMessageId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLeaveMessage() {
        return this.leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

}
