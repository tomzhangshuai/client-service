package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.InvitationUserInfo;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.entity.UserReward;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User:WangZhiyuan
 * Data:2017/08/04
 * Time:13:59
 */
public interface RecommendedPrizeService {
    /**
     * 推荐有奖,被邀请成功的用户信息
     *
     * @param userId 传递用户id，奖励类型;
     *               return
     */
    Map getRecommendedPrize(long userId);
}
