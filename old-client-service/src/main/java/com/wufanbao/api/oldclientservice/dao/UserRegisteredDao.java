package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.User;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * User:Wangshihao
 * Date:2017-08-01
 * Time:10:21
 */
public interface UserRegisteredDao {

    //注册方法
    Integer add(UserRegistered userRegistered);

    //用来检测数据库是不是已经存在此手机号
    UserRegistered checkMB(String mB);

    //更改用户余额
    int updateBalance(@Param("userId") long userId, @Param("balance") double balance, @Param("usableBalance") double usableBalance);


    //获取用户余额
    UserRegistered querybalance(long userId);

    //检查支付密码是否正确
    UserRegistered queryPayPwd(@Param("userId") long userId, @Param("paypassword") String paypassword);

    /**
     * 用户注册
     *
     * @param userId              用户Id
     * @param parentUserId        用户父ID
     * @param userName            用户姓名
     * @param loginNo             登录名
     * @param password            登录密码
     * @param payPassword         支付密码
     * @param userType            用户类型
     * @param mb                  手机号码
     * @param integral            积分
     * @param balance             余额
     * @param usableBalance       可用余额
     * @param lockBalance         锁定余额
     * @param invitationCode      邀请码
     * @param isActive            是否启用
     * @param resetPasswordNeeded 是否需要重置密码
     * @param isAuth              是否认证
     * @param isCredit            是否开启授信
     * @param creditAmount        授信总额
     * @param creditUsableAmount  可用授信总额
     * @param creditLimit         授信规则
     * @param companyPayment      是否企业用户
     * @param sex                 性别
     * @return int
     */
    int addUser(@Param("userId") long userId, @Param("parentUserId") long parentUserId, @Param("userName") String userName,
                @Param("loginNo") String loginNo,
                @Param("password") String password,
                @Param("payPassword") String payPassword, @Param("userType") int userType,
                @Param("mb") String mb, @Param("integral") int integral, @Param("balance") int balance, @Param("usableBalance") int usableBalance,
                @Param("lockBalance") int lockBalance, @Param("invitationCode") String invitationCode, @Param("isActive") int isActive,
                @Param("resetPasswordNeeded") int resetPasswordNeeded, @Param("isAuth") int isAuth, @Param("isCredit") int isCredit,
                @Param("creditAmount") int creditAmount, @Param("creditUsableAmount") int creditUsableAmount, @Param("creditLimit") int creditLimit,
                @Param("companyPayment") int companyPayment, @Param("sex") int sex);

    /**
     * 查询用户是否是主账户
     *
     * @param userId 用户ID
     * @return java.lang.Long
     * @date 2018/6/29
     */
    Long getUserParentId(@Param("userId") Long userId);
}
