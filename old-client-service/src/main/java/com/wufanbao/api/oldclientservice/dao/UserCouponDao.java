package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * User:WangZhiyuan
 * Data:2017/08/02
 * Time:14:35
 */
public interface UserCouponDao {
    /**
     * 查询用户有多少张可以用的优惠券
     *
     * @param userId 用户id
     * @return 优惠券集合
     */
    List<UserCoupon> userCouponDaoRow(@Param("userId") long userId);

    /**
     * 获取所有优惠券的信息
     *
     * @param userId 用户id
     * @return 所有优惠券信息的集合
     */
    List<UserCouponInfo> getUserCouponInfo(@Param("userId") long userId);

    /**
     * 获取满足时间需求的优惠券
     *
     * @param userId
     * @return
     */
    List<UserCouponInfo> getUseUserCouponInfo(@Param("userId") long userId);

    /**
     * 根据城市名称获取城市id
     *
     * @param areaName
     * @return
     */
    long getAreaId(@Param("areaName") String areaName);

    /**
     * 退换优惠券
     */
    int updateCoupon(@Param("userId") long userId, @Param("couponId") long couponId, @Param("status") long status);

    /**
     * 根据用户优惠券表id修改用户优惠券状态
     *
     * @param couponId 用户优惠券表id
     * @param status   用户优惠券状态
     * @return
     */
    int updateUserCouponStatus(@Param("couponId") long couponId, @Param("status") int status);

    /**
     * 修改用户券使用数量
     *
     * @param couponDefinitionId 优惠券定义表id
     * @return
     */
    int updateCouponDefinitionUsed(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 插入优惠券信息
     *
     * @param userCoupon 用户优惠券信息
     * @return
     */
    int insertUserCoupon(UserCoupon userCoupon);

    /**
     * 根据优惠券定义表id查询优惠券信息
     *
     * @param couponDefinitionId 优惠券定义表id
     * @return
     */
    UserCouponInfo selectCouponInfo(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 发放优惠券
     *
     * @param couponDefinitionId
     * @return
     */
    int gotCoupon(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 根据订单id去查看该用户是不是被邀请的
     *
     * @param userOrderId 用户订单id
     * @return
     */
    UserInvitation getUserInvitation(@Param("userOrderId") long userOrderId);

    /**
     * 获取奖励源id
     *
     * @param invitedUserId
     * @param userId
     * @return
     */
    long getInvitationId(@Param("invitedUserId") long invitedUserId, @Param("userId") long userId);

    /**
     * 插入用户奖励信息
     *
     * @param userReward
     * @return
     */
    int insertUserReward(UserReward userReward);

    /**
     * 用户配额
     *
     * @param userId
     * @return
     */
    UserQuota getUserQuota(@Param("userId") long userId);

    /**
     * 获取优惠券金额
     *
     * @param couponDefinitionId
     * @return
     */
    BigDecimal getCouponAmount(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 作废优惠券
     *
     * @return
     */
    int cancellationCoupon();

    /**
     * 扫描二维码发放优惠券
     *
     * @param tempId 二维码Id
     * @param userId 用户ID
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/21
     */
    int updateTemp(@Param("tempId") long tempId, @Param("userId") long userId);

    /**
     * 查询优惠券信息根据优惠券ID
     *
     * @param userId
     * @param couponId
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/5/10
     */
    Map getUserCouponInfoByCouponId(@Param("userId") long userId, @Param("couponId") long couponId);

}
