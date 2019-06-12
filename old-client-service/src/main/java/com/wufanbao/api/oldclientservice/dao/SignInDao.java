package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserSignIn;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

public interface SignInDao {
    /**
     * 用户签到
     *
     * @param userId
     * @return
     */
    int signIn(@Param("userId") long userId);

    /**
     * 用户签到记录
     *
     * @param userId
     * @param signedDay
     * @param integral
     * @return
     */
    int signInLog(@Param("userId") long userId,
                  @Param("signedDay") String signedDay,
                  @Param("integral") int integral);

    /**
     * 用户签到信息
     *
     * @param userId
     * @return
     */
    Map selectUserSignIn(@Param("userId") long userId);

    /**
     * 用户是否签到
     *
     * @param userId
     * @return
     */
    boolean ifSignIn(@Param("userId") long userId);


}
