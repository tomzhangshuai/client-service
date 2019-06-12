package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserAuth;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;

/**
 * User:Wangshihao
 * Date:2017-08-04
 * Time:17:38
 */
public interface UserAuthDao {
    /**
     * 添加用户信息
     *
     * @param userAuth
     * @return
     */
    public int insertUserAuth(UserAuth userAuth);

    public UserAuth checkCardNo(String cardNo);

}
