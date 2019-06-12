package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserBank;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:17:24
 */
public interface GetUserInfoDao {
    /**
     * 根据用户UserId获取用户信息
     *
     * @param userId 用户UserId
     * @return
     */
    UserRegistered getUserInfo(@Param("userId") long userId);

    /**
     * 根据用户UserId获取银行卡是否被绑定
     *
     * @param userId 用户UserId
     * @return
     */
    List<UserBank> getUserBankInfo(@Param("userId") long userId);

    /**
     * 根据用户userId 获取用户当前便当币数量
     *
     * @param userId 用户UserId
     * @return
     */
    BigDecimal getUserUsableBalance(@Param("userId") long userId);

    /**
     * 根据手机号查询用户ID
     *
     * @param mb 手机号
     * @return java.lang.Long
     * @author Wang Zhiyuan
     * @date 2018/5/14
     */
    Long getUserIdByMb(@Param("mb") String mb);


}
