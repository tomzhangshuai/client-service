package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.UserLikeDao;
import com.wufanbao.api.oldclientservice.entity.UserLike;
import com.wufanbao.api.oldclientservice.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-10
 * Time:9:15
 */
@Transactional
@Service
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private UserLikeDao userLikeDao;

    @Override
    public List<UserLike> querylike(UserLike userLike) {
        return userLikeDao.querylike(userLike);
    }

    @Override
    public List<UserLike> query(long articleId) {
        return userLikeDao.query(articleId);
    }

    @Override
    public List<UserLike> queryuser(long userId) {
        return userLikeDao.queryuser(userId);
    }

    @Override
    public int addlike(UserLike userLike) {
        return userLikeDao.addlike(userLike);
    }

    @Override
    public void deletelike(long userId) {
        userLikeDao.deletelike(userId);
    }
}
