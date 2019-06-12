package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.entity.UserCoupon;
import com.wufanbao.api.oldclientservice.entity.UserOrder;
import org.apache.ibatis.annotations.Param;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * user:WangZhiyuan
 */
public interface UserCouponService {
    List<UserCoupon> userCouponDaoRow(long userId);

    /**
     * 获取所有优惠券的信息
     *
     * @param userId
     * @return
     */
    Map getUserCouponInfo(long userId);

    /**
     * 可以使用的复合时间条件的优惠券
     *
     * @param userOrder         用户订单信息
     * @param productGlobalList 订单内详细商品
     * @return
     */
    Map getUseUserCouponInfo(UserOrder userOrder, String productGlobalList, String areaName) throws Exception;

    /**
     * 退换优惠券
     */
    int updateCoupon(long userId, long couponId, int status);

    /**
     * 邀请注册的链接
     *
     * @param userId 用户id
     * @param mb     手机号
     * @param code   验证码
     * @return
     */
    Map inviteRegister(long userId, String mb, String code);

    /**
     * 验证手机号是否注册没有注册的时候给用户发送验证码
     *
     * @param Mb 手机号
     * @return
     * @throws IOException
     */
    Map checkMb(String Mb) throws IOException;

    /**
     * 邀请用户奖励
     *
     * @param userOrderId
     * @return
     */
    Map InviteRewards(long userOrderId);

    /**
     * 获取发现分享数据
     *
     * @param appDiscoverId
     * @return
     */
    ResponseInfo appDiscoverShare(long appDiscoverId);

    /**
     * 用户优惠券详情
     *
     * @param x        经线
     * @param y        纬线
     * @param areaName 城市名称
     * @param couponId 优惠券定义表ID
     * @param userId   用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/10
     */
    ResponseInfo userCouponDetails(String x, String y, String areaName, long couponId, long userId);

}
