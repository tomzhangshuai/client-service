package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.User;
import com.wufanbao.api.oldclientservice.entity.UserIntegralLog;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:15:25
 */
public interface UpdateUserInfoService {
    //修改用户名
    int updateUserName(UserRegistered vo);

    //修改用户手手机号
    int updateUserMb(UserRegistered vo);

    //修改支付密码
    int updateUserPayPassword(UserRegistered vo);

    //修改登录密码
    int updateUserPassword(UserRegistered vo);

    /**
     * 根据手机号修改密码
     *
     * @param mB
     * @return
     */
    int updateUserPasswordByMb(String mB, String passWord);


    /**
     * 更新用户信息
     *
     * @param sex
     * @param birthday
     * @param breakFast
     * @param lunch
     * @param dinner
     * @return
     */
    ResponseInfo updateUserSex(int sex,
                               Date birthday,
                               Date breakFast,
                               Date lunch,
                               Date dinner,
                               long userId);

    /**
     * 用户信息完善程度
     *
     * @param userId
     * @return
     */
    ResponseInfo perfectDegree(long userId);

    /**
     * 用户积分使用记录
     *
     * @param userId
     * @return
     */
    ResponseInfo getUserIntegralLog(@Param("userId") long userId);


}
