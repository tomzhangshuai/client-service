package com.wufanbao.api.oldclientservice.entity;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:15:37
 */
public class Userleavemessage {
    private long userId;
    private String leaveMessage;
    private long leaveMessageId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public void setLeaveMessage(String leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    public long getLeaveMessageId() {
        return leaveMessageId;
    }

    public void setLeaveMessageId(long leaveMessageId) {
        this.leaveMessageId = leaveMessageId;
    }
}
