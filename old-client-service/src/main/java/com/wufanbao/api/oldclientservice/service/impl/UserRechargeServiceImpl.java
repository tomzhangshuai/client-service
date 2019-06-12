package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserRechargeDao;
import com.wufanbao.api.oldclientservice.entity.UserRecharge;
import com.wufanbao.api.oldclientservice.service.UserRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User:Wangshihao
 * Date:2017-09-19
 * Time:20:37
 */
@Service
@Transactional
public class UserRechargeServiceImpl implements UserRechargeService {

    @Autowired
    private UserRechargeDao userRechargeDao;

    @Override
    public void beforeUserRecharge(UserRecharge userRecharge) {
        userRechargeDao.beforeUserRecharge(userRecharge);
    }

    @Override
    public int afterUserRecharge(UserRecharge userRecharge) {
        return userRechargeDao.afterUserRecharge(userRecharge);
    }

    @Override
    public UserRecharge queryUserRecharge(long rechargeId) {
        return userRechargeDao.queryUserRecharge(rechargeId);
    }
}
