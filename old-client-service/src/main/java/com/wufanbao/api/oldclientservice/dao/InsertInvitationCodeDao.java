package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserInvitation;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.entity.UserReward;
import com.wufanbao.api.oldclientservice.entity.UserRewardInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:WangZhiyuan
 * Data:2017/08/04
 */
public interface InsertInvitationCodeDao {
    /**
     * 根据邀请码去查询邀请用户id
     *
     * @param invitationCode 邀请码
     * @return 邀请人userId
     */
    UserRegistered getUserId(@Param("invitationCode") String invitationCode);

    /**
     * 将邀请（邀请方式为填写邀请码的）信息插入到邀请表中
     *
     * @param vo 邀请信息
     * @return 执行结果
     */
    int insertUserInvitation(UserInvitation vo);

    /**
     * 根据用户id去查询邀请成功的用户被邀请
     *
     * @param invitedUserId 邀请码
     * @return List 成功邀请的信息
     */
    List<UserInvitation> getUserInvitation(@Param("invitedUserId") long invitedUserId);

    /**
     * 获取用户奖励信息
     *
     * @return
     */
    List<UserReward> getRewardInfo();

    /**
     * 根据用户id获取用户邀请记录
     *
     * @param userId
     * @return
     */
    List<UserRewardInfo> rewardRecord(@Param("userId") long userId);
}
