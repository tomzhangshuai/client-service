package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.User;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:15:24
 */
public interface UpdateUserInfoDao {
    /**
     * 修改用户名
     *
     * @param vo 传递用户id，奖励类型;
     */
    int updateUserName(UserRegistered vo);

    /**
     * 修改用户手手机号
     *
     * @param vo 传递用户id，奖励类型;
     */
    int updateUserMb(UserRegistered vo);

    /**
     * 修改支付密码
     *
     * @param vo 传递用户id，奖励类型;
     */
    int updateUserPayPassword(UserRegistered vo);

    /**
     * 修改登录密码
     *
     * @param vo 传递用户id，奖励类型;
     */
    int updateUserPassword(UserRegistered vo);

    /**
     * 根据手机号修改密码
     *
     * @param mB
     * @param passWord
     * @return
     */
    int updateUserPasswordByMb(@Param("mB") String mB, @Param("passWord") String passWord);

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
    int updateUserSex(@Param("sex") int sex,
                      @Param("birthday") Date birthday,
                      @Param("breakFast") Date breakFast,
                      @Param("lunch") Date lunch,
                      @Param("dinner") Date dinner,
                      @Param("userId") long userId);

    /**
     * 用户信息完善程度
     *
     * @param userId
     * @return
     */
    UserRegistered perfectDegree(@Param("userId") long userId);

    /**
     * 更改主账户余额
     *
     * @param userId
     * @param amount
     * @return
     */
    int updateMasterBalance(@Param("userId") long userId, @Param("amount") BigDecimal amount);
}
