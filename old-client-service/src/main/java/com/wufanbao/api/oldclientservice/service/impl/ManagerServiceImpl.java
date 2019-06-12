package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.ManagerDao;
import com.wufanbao.api.oldclientservice.entity.Manager;
import com.wufanbao.api.oldclientservice.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-07-31
 * Time:10:06
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    //自动装载dao
    @Autowired
    private ManagerDao dao;

    @Override
    public List<Manager> getSelect() {
        return dao.managerList();
    }

}
