package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserLike;

import java.util.List;

/**
 * User:Wangshihao
 * Date:2017-08-10
 * Time:9:14
 */
public interface UserLikeService {
    /**
     * 获取点赞数
     */
    public List<UserLike> querylike(UserLike userLike);

    public List<UserLike> query(long articleId);

    public List<UserLike> queryuser(long userId);

    /**
     * 点赞成功
     */
    public int addlike(UserLike userLike);

    /**
     * 取消点赞
     */
    public void deletelike(long userId);
}
