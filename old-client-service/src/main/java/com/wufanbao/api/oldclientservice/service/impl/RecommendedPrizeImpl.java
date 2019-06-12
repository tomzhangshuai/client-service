package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.RecommendedPrizeDao;
import com.wufanbao.api.oldclientservice.dao.UserOrderDao;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.RecommendedPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user:WangZhiyuan
 */
@Service
public class RecommendedPrizeImpl implements RecommendedPrizeService {
    @Autowired
    private RecommendedPrizeDao recommendedPrizeDao;
    @Autowired
    private UserOrderDao userOrderDao;

    /**
     * 推荐有奖,被邀请成功的用户信息
     *
     * @param userId 传递用户id，奖励类型;
     * @return
     */
    @Override
    public Map getRecommendedPrize(long userId) {
        Map map = new HashMap();
        //获取用户邀请表的信息
        List<UserReward> uRList = recommendedPrizeDao.getUserReward(userId);
        //查询是否有交易完成的用户订单
        List<UserOrder> userOrderList = userOrderDao.judgeOrder(userId);
        RewardInfo rew = new RewardInfo();
        //如果没有交易完成订单不能显示邀请码
        if (userOrderList.size() > 0) {
            if (uRList.size() > 0) {
                //累计收益的便当币
                BigDecimal rewardValue = recommendedPrizeDao.getEarningsLunchMoney(userId);
                //所有被邀请成功用户的信息
                List<InvitationUserInfo> iUIList = recommendedPrizeDao.getInvitationUserInfo(userId);
                rew.setiUIList(iUIList);
                rew.setNoInfo(1);
                rew.setRewardValue(rewardValue);
                rew.setSucceedNumber(iUIList.size());
                map.put("type", 1);
                map.put("rew", rew);
            } else {
                rew.setNoInfo(0);
                map.put("type", 1);
                map.put("rew", rew);
            }
        } else {
            map.put("type", 0);
        }
        return map;
    }
}
