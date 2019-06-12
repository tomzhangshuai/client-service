package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.AppDiscoverDao;
import com.wufanbao.api.oldclientservice.entity.AppDiscover;
import com.wufanbao.api.oldclientservice.service.AppDiscoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:wangshihao
 * Date:2017-12-18
 * Time:10:46
 */
@Service
public class AppDiscoverServiceImpl implements AppDiscoverService {
    @Autowired
    private AppDiscoverDao appDiscoverDao;

    @Override
    public List<AppDiscover> queryAppDiscover() {
        return appDiscoverDao.queryAppDiscover();
    }
}
