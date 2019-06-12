package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserBankDao;
import com.wufanbao.api.oldclientservice.entity.UserBank;
import com.wufanbao.api.oldclientservice.service.UserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:14:31
 */
@Transactional
@Service
public class UserBankServiceImpl implements UserBankService {

    @Autowired
    private UserBankDao userBankDao;

    @Override
    public Integer insertUserBank(UserBank userBank) {
        return userBankDao.insertUserBank(userBank);
    }

    @Override
    public List<UserBank> selectUserBank(long userId) {
        return userBankDao.selectUserBank(userId);
    }

    @Override
    public void deleteUserBank(String account) {
        userBankDao.deleteUserBank(account);
    }

    @Override
    public UserBank checkAccount(String account) {
        return userBankDao.checkAccount(account);
    }
}
