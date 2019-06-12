package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserInvitation,用户邀请信息
public class UserInvitation {
    //InvitationId,
    private long invitationId;
    //邀请人,
    private long userId;
    //被邀请人,
    private long invitedUserId;
    //邀请方式,
    private InviteType inviteType;
    //邀请信息,
    private String inviteInfo;
    //发生时间,
    private Date createTime;

    public long getInvitationId() {
        return this.invitationId;
    }

    public void setInvitationId(long invitationId) {
        this.invitationId = invitationId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInvitedUserId() {
        return this.invitedUserId;
    }

    public void setInvitedUserId(long invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    public InviteType getInviteType() {
        return this.inviteType;
    }

    public void setInviteType(InviteType inviteType) {
        this.inviteType = inviteType;
    }

    public String getInviteInfo() {
        return this.inviteInfo;
    }

    public void setInviteInfo(String inviteInfo) {
        this.inviteInfo = inviteInfo;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
