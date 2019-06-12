package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserRecharge;

/**
 * User:Wangshihao
 * Date:2017-09-19
 * Time:20:36
 */
public interface UserRechargeService {
    /**
     * 充值成功前
     *
     * @param userRecharge
     * @return
     */
    public void beforeUserRecharge(UserRecharge userRecharge);

    /**
     * 充值成功后
     */
    public int afterUserRecharge(UserRecharge userRecharge);

    /**
     * 查询用户id
     */
    public UserRecharge queryUserRecharge(long rechargeId);
}
