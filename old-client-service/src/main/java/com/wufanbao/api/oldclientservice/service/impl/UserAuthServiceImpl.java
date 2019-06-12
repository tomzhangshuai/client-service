package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserAuthDao;
import com.wufanbao.api.oldclientservice.entity.UserAuth;
import com.wufanbao.api.oldclientservice.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:17:42
 */
@Transactional
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public int insertUserAuth(UserAuth userAuth) {
        return userAuthDao.insertUserAuth(userAuth);
    }

    @Override
    public UserAuth check(String cardNo) {
        return userAuthDao.checkCardNo(cardNo);
    }
}
