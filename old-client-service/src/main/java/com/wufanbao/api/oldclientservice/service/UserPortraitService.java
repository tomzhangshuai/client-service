package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserRegistered;

/**
 * User:Wangshihao
 * Date:2017-08-03
 * Time:10:39
 */

public interface UserPortraitService {
    /**
     * 修改头像
     *
     * @param UserRegistered
     * @return
     */
    public void updatePortrait(UserRegistered userRegistered);

    /**
     * 修改user表用户实名是否认证
     *
     * @param userRegistered
     */
    public void updateisAuth(UserRegistered userRegistered);
}
