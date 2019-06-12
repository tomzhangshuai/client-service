package com.wufanbao.api.oldclientservice.dao;


import com.wufanbao.api.oldclientservice.entity.UserRegistered;

/**
 * User:Wangshihao
 * Date:2017-08-03
 * Time:10:24
 */
public interface UserPortrait {
    /**
     * 修改头像
     */
    public void updatePortrait(UserRegistered userRegistered);

    /**
     * 更新user表是否认证
     */
    public void updateisAuth(UserRegistered userRegistered);
}
