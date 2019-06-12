package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserPortrait;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.service.UserPortraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User:Wangshihao
 * Date:2017-08-03
 * Time:10:44
 */
@Transactional
@Service
public class UserPortraitServiceImpl implements UserPortraitService {

    @Autowired
    private UserPortrait dao;

    @Override
    public void updatePortrait(UserRegistered userRegistered) {
        dao.updatePortrait(userRegistered);
    }

    @Override
    public void updateisAuth(UserRegistered userRegistered) {
        dao.updateisAuth(userRegistered);
    }

}
