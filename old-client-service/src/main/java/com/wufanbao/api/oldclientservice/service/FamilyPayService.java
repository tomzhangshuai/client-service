package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Wang Zhiyuan
 */
public interface FamilyPayService {
    /**
     * 验证用户绑定信息
     *
     * @param mb     手机号码
     * @param userId 用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     */
    ResponseInfo userBindingInfo(String mb, long userId);

    /**
     * 给用户发送验证码
     *
     * @param mb 手机号码
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @throws IOException
     * @author Wang Zhiyuan
     */
    ResponseInfo checkMb(String mb) throws IOException;

    /**
     * 确认验证码
     *
     * @param mb        手机号码
     * @param code      验证码
     * @param userId    用户ID
     * @param sonUserId 子用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     */
    ResponseInfo checkCode(String mb, String code, long userId, long sonUserId);

    /**
     * 查询主账户下的子账户信息
     *
     * @param masterUserId 主用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     */
    ResponseInfo selectUserFamilyPayInfo(long masterUserId);

    /**
     * 删除用户家庭付信息
     *
     * @param userId       用户ID
     * @param masterUserId 主用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     */
    ResponseInfo deleteSonUser(long userId, long masterUserId);

    /**
     * 修改用户账户配置
     *
     * @param quotaType    家庭付类型
     * @param limitQuota   限制额度
     * @param totalQuota   总额度
     * @param disabled     是否启用
     * @param userId       用户ID
     * @param masterUserId 主账户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     */
    ResponseInfo updateFamilyPayInfo(Integer quotaType, Boolean limitQuota, BigDecimal totalQuota, Boolean disabled, long userId, long masterUserId);

    /**
     * 家庭付支付
     *
     * @param userId      用户ID
     * @param amount      金额
     * @param userOrderId 用户订单ID
     * @return java.util.Map<java.lang.String   ,   java.lang.Integer>
     * @author Wang Zhiyuan
     */
    Map<String, Integer> familyPay(long userId, BigDecimal amount, long userOrderId);

    /**
     * 确认绑定
     *
     * @param userId      用户ID
     * @param mb          用户手机号
     * @param payPassword 支付密码
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/6/26
     */
    ResponseInfo confirmBindingFamily(long userId, String mb, String payPassword);

    /**
     * 解绑用户关系
     *
     * @param userId          用户ID
     * @param relevanceUserId 父ID或者子ID
     * @return com.allpha.entity.ResponseInfo
     */
    ResponseInfo releaseRelationship(long userId, Long relevanceUserId);
}
