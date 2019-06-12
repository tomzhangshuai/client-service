package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserInvitation;

import java.util.List;
import java.util.Map;

/**
 * user:WangZhiyuan
 */
public interface InsertInvitationCodeService {
    /**
     * 根据用户id去查询邀请成功的用户被邀请
     *
     * @param invitedUserId 邀请码
     * @return List 成功邀请的信息
     */
    List<UserInvitation> getUserInvitation(long invitedUserId);

    /**
     * 填写邀请码
     *
     * @param invitationCode 邀请人的邀请码
     * @param userId         被邀请人的用户id
     * @return
     */
    Map InsertInvitationCode(String invitationCode, long userId);

    /**
     * 奖励轮播
     *
     * @return
     */
    Map rewardRotation();

    /**
     * 奖励记录
     *
     * @param userId
     * @return
     */
    Map rewardRecord(long userId);
}
