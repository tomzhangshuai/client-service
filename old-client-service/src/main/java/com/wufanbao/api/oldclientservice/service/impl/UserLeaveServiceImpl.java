package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserLeaveMessageDao;
import com.wufanbao.api.oldclientservice.entity.Userleavemessage;
import com.wufanbao.api.oldclientservice.service.UserLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:15:43
 */
@Transactional
@Service
public class UserLeaveServiceImpl implements UserLeaveService {

    @Autowired
    private UserLeaveMessageDao dao;

    @Override
    public Integer addleave(Userleavemessage userleavemessage) {
        return dao.addleave(userleavemessage);
    }
}
