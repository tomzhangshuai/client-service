package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.UserAuth;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:17:41
 */
public interface UserAuthService {
    /**
     * 添加用户信息
     *
     * @param userAuth
     * @return
     */
    public int insertUserAuth(UserAuth userAuth);

    public UserAuth check(String cardNo);
}
