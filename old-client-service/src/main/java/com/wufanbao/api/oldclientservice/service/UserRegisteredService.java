package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.User;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;

import java.math.BigDecimal;

/**
 * User:Wangshihao
 * Date:2017-08-01
 * Time:10:25
 */
public interface UserRegisteredService {

    //用户注册
    public Integer add(UserRegistered userRegistered);

    // 通过手机号来判断是否以注册
    public UserRegistered check(String mB);

    //更改用户余额
    int updateBalance(long userId, double balance, double usableBalance);

    //获取用户余额
    public UserRegistered querybalance(long userId);

    //检查支付密码是否正确
    public UserRegistered queryPayPwd(long userId, String paypassword);

    /**
     * 添加一个用户
     *
     * @param mb       手机号
     * @param password 登录密码
     * @return com.wufanbao.api.oldclientservice.tool.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/13
     */
    ResponseInfo addUser(String mb, String password, long userId);

}
