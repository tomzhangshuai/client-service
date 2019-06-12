package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.InvitationUserInfo;
import com.wufanbao.api.oldclientservice.entity.UserReward;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * User:WangZhiyuan
 * Data:2017/08/04
 */
public interface RecommendedPrizeDao {
    /**
     * 累计收益便当币
     *
     * @param userId 传递用户id，奖励类型;
     * @return
     */
    BigDecimal getEarningsLunchMoney(@Param("userId") long userId);

    /**
     * 用户邀请表信息
     *
     * @param userId 传递用户id，奖励类型;
     * @return
     */
    List<UserReward> getUserReward(@Param("userId") long userId);

    /**
     * 根据用户id去查询被邀请用户的信息
     *
     * @param userId 传递用户id，奖励类型;
     * @return
     */
    List<InvitationUserInfo> getInvitationUserInfo(@Param("userId") long userId);

}
