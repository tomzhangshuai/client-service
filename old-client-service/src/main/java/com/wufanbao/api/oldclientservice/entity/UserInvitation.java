package com.wufanbao.api.oldclientservice.entity;

import java.sql.Date;

/**
 * User:WangZhiyuan
 * Data:2017/08/04
 */
public class UserInvitation {
    private long invitationId;//邀请表id;
    private long userId;//邀请人;
    private long invitedUserId;//被邀请人;
    private int inviteType;//邀请方式
    private String inviteInfo;//邀请信息;
    private Date createTime;//发生时间;

    public long getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(long invitationId) {
        this.invitationId = invitationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInvitedUserId() {
        return invitedUserId;
    }

    public void setInvitedUserId(long invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    public int getInviteType() {
        return inviteType;
    }

    public void setInviteType(int inviteType) {
        this.inviteType = inviteType;
    }

    public String getInviteInfo() {
        return inviteInfo;
    }

    public void setInviteInfo(String inviteInfo) {
        this.inviteInfo = inviteInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
