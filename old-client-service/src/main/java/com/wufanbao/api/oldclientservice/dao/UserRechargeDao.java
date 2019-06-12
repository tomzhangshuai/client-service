package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserRecharge;
import org.apache.ibatis.annotations.Param;

/**
 * User:Wangshihao
 * Date:2017-09-19
 * Time:20:21
 */
public interface UserRechargeDao {

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
