package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Repository
public interface UserDao {
    /**
     * 根据主键获取用户数据
     *
     * @param userId
     * @return
     */
    User getUser(@Param("userId") long userId);

    /**
     * 获取用户登记
     *
     * @param userId
     * @return
     */
    User getUserGradeValue(@Param("userId") long userId);

    /**
     * 根据用户成长值获取积分特权
     *
     * @param gradeValue
     * @param privilege
     * @return
     */
    Data getUserGradePrivilege(@Param("gradeValue") long gradeValue, @Param("privilege") long privilege);

    /**
     * 完善用户信息
     *
     * @param userId
     * @param sex
     * @param birthday
     * @param breakFast
     * @param lunch
     * @param dinner
     * @return
     */
    int perfectUser(@Param("userId") long userId,
                    @Param("userName") String userName,
                    @Param("sex") int sex,
                    @Param("birthday") Date birthday,
                    @Param("breakFast") Date breakFast,
                    @Param("lunch") Date lunch,
                    @Param("dinner") Date dinner);

    /**
     * 获取用户的优惠券数
     *
     * @param userId
     * @return
     */
    int getUserCouponSize(@Param("userId") long userId);

    int getUserMessgeSize(@Param("userId") long userId);

    /**
     * 获取最近的一次签到记录
     *
     * @param userId
     * @return
     */
    UserSignIn getUserSignin(@Param("userId") long userId);

    UserSignInLog getUserSigninLog(@Param("userId") long userId);
    /**
     * 签到
     *
     * @param userId
     * @param continuousDays
     * @return
     */
    int userSignin(@Param("userId") long userId, @Param("continuousDays") int continuousDays);

    /**
     * 用户连续签到
     *
     * @param userId
     * @return
     */
    int userContinuitySignin(@Param("userId") long userId);

    /**
     * 更新最大连续签到
     *
     * @param userId
     * @param maxContinuousDays
     * @return
     */
    int updateMaxContinuousSignin(@Param("userId") long userId, @Param("maxContinuousDays") long maxContinuousDays);

    /**
     * 首次签到
     *
     * @param userId
     * @return
     */
    int insertUserSignin(@Param("userId") long userId);

    /**
     * 绑定用户openId
     * @param openId
     * @return
     */
    int updateUserbindingOpenId(@Param("userId") long userId,@Param("openId") String openId);
    /**
     * 签到日志
     *
     * @param userId
     * @param signedDay
     * @param integral
     * @return
     */
    int addSignInLog(@Param("userId") long userId, @Param("signedDay") String signedDay, @Param("integral") long integral);

    //修改用户积分（积分成长）
    int addIntegral(@Param("userId") long userId, @Param("quantity") int quantity);

    //使用积分
    int reduceIntegral(@Param("userId") long userId, @Param("quantity") int quantity);

    //根据积分日志源，获取用户积分日志
    UserIntegralLog getUserIntegralLogBySourceType(@Param("userId") long userId, @Param("sourceType") String sourceType);

    //添加积分记录
    int insertIntegralLog(@Param("integralLogId") long integralLogId, @Param("userId") long userId, @Param("quantity") int quantity, @Param("sourceType") String sourceType, @Param("sourceId") long sourceId, @Param("description") String description);

    //修改用户成长值
    int updateGradeGrowUp(@Param("userId") long userId, @Param("gradeValue") int gradeValue);

    //根据成长值日志源，获取用户成长值日志
    UserGradeLog getUserGradelogBySourceType(@Param("userId") long userId, @Param("sourceType") String sourceType);

    //添加用户成长记录
    int insertUserGradeLog(
            @Param("userGradeLogId") long userGradeLogId,
            @Param("userId") long userId,
            @Param("gain") int gain,
            @Param("sourceType") String sourceType,
            @Param("sourceId") long sourceId,
            @Param("description") String description
    );

    /**
     * 获取用户资金交易型记录
     *
     * @param userId
     * @return
     */
    List<UserCapitalLog> getUserCapitallogs(@Param("userId") long userId, @Param("month") String month);

    //根据获取家庭付
    UserCapitalLog getUserCapitallogBysourceType(@Param("userOrderId") long userOrderId, @Param("sourceType") String sourceType);

    /**
     * 获取子帐户自己交易记录
     *
     * @param userId
     * @param month
     * @return
     */
    List<Data> getSonUserCapitalLogs(@Param("userId") long userId, @Param("month") String month);

    /**
     * 获取用户的积分日志
     *
     * @param userId
     * @return
     */
    List<UserIntegralLog> getUserIntegralLogs(@Param("userId") long userId, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 获取积分商场商品
     *
     * @return
     */
    List<IntegralCommodity> getIntegralCommoditys(@Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 兑换积分商品
     *
     * @param integralCommodityId
     * @return
     */
    int updateIntegralCommodity(@Param("integralCommodityId") long integralCommodityId, @Param("quantity") int quantity);


    IntegralCommodity getIntegralCommodity(@Param("integralCommodityId") long integralCommodityId);

    /**
     * 获取用户的积分兑换记录
     *
     * @param userId
     * @return
     */
    List<IntegralExchange> getUserIntegralexchanges(@Param("userId") long userId, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

    /**
     * 获取所有用户最新的兑换记录
     *
     * @return
     */
    List<Data> getIntegralexchanges();

    /**
     * 添加积分兑换记录
     *
     * @param integralExchangeId
     * @param userId
     * @param integralCommodityId
     * @param amount
     * @param description
     * @return
     */
    int addIntegralExchangeLog(@Param("integralExchangeId") long integralExchangeId, @Param("userId") long userId, @Param("integralCommodityId") long integralCommodityId, @Param("amount") BigDecimal amount, @Param("description") String description);

    int addUserMessage(@Param("userId") long userId, @Param("messageInfoId") long messageInfoId);

    /**
     * 获取消息通知
     *
     * @param userId
     * @return
     */
    List<Data> getUserMessages(@Param("userId") long userId, @Param("isRead") int isRead, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

    /**
     * 设置消息已读
     *
     * @param userId
     * @return
     */
    int setUserMessgeRead(@Param("userId") long userId);

    /**
     * 设置消息已删除
     *
     * @param userId
     * @return
     */
    int setUserMessgeDelete(@Param("userId") long userId);

    /**
     * 使用余额
     *
     * @param userId
     * @return
     */
    int useBalance(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 充值
     *
     * @param userId
     * @param amount
     * @return
     */
    int recharge(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 退还余额
     *
     * @param userId
     * @return
     */
    int refundBalance(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    long insertUser(User user);

    /**
     * 根据登录账号获取用户数据，这里登录号唯一性
     *
     * @param loginNo
     * @return
     */
    User getUserByLoginNo(@Param("loginNo") String loginNo);

    /**
     * 判断是否绑定第三方账号
     *
     * @param bindingId
     * @return
     */
    UserBinding getUserBindingByBindingId(@Param("bindingId") String bindingId);

    String getBindingIdByUserId(@Param("userId") long userId);

    /**
     * 更新User表的手机号和登陆号
     *  @param loginNo
     *  bindingId userId
     *  @return
     */
    int updateUserMb(@Param("loginNo")String  loginNo,@Param("userId")long userId);

    /**
     * 绑定第三方账号登录
     *
     * @param userId
     * @param bindingId
     * @param bindingType
     * @return
     */
    int insertUserBinding(@Param("userId") long userId, @Param("bindingId") String bindingId, @Param("bindingType") int bindingType);

    /**
     * 扫一扫支付绑定
     * @param userId
     * @param bindingId
     * @param openId
     * @param bindingType
     * @return
     */
    int insertUserBindingOpenId(@Param("userId") long userId, @Param("bindingId") String bindingId, @Param("openId") String openId,@Param("bindingType") int bindingType);
    /**
     * 解除第三方登录绑定关系
     * @param userId
     * @param bindingId
     * @return
     */

    int deleteUserBinding(@Param("userId") long userId, @Param("bindingId") String bindingId);

    /**
     * 家庭付，获取当前绑定的子账号
     *
     * @param userId
     * @return
     */
    List<Data> getSonUsers(@Param("userId") long userId);

    /**
     * 获取用户家庭付额度信息
     *
     * @param userId
     * @return
     */
    Data getFamilyPay(@Param("userId") long userId);

    /**
     * 获取家庭付主账号可用额度
     *@param userId
     * @return
     */
    Data getFamilyPayTest(@Param("userId") long userId);
    /**
     * 绑定／解绑 用户
     *
     * @param userId
     * @param parentUserId
     * @return
     */
    int bindUser(@Param("userId") long userId, @Param("parentUserId") long parentUserId);

    /**
     * 删除用户绑定关系
     *
     * @param userId
     * @param masterUserId
     * @return
     */
    int deleteUserfamilypayrelation(@Param("userId") long userId, @Param("masterUserId") long masterUserId);

    int updateUserfamilypayrelation(@Param("masterUserId") long masterUserId,
                                    @Param("userId") long userId,
                                    @Param("quotaType") Integer quotaType,
                                    @Param("limitQuota") Boolean limitQuota,
                                    @Param("totalQuota") BigDecimal totalQuota,
                                    @Param("consumeQuota") BigDecimal consumeQuota,
                                    @Param("updateTime") Date updateTime);

    //清除每日限额
    int clearFamilyLimit(@Param("quotaType") Integer quotaType);

    /**
     * 添加用户绑定关系
     *
     * @param masterUserId
     * @param userId
     * @param quotaType
     * @param limitQuota
     * @param totalQuota
     * @param consumeQuota
     * @param totalAmount
     * @param disabled
     * @return
     */
    int insertUserfamilypayrelation(@Param("masterUserId") long masterUserId,
                                    @Param("userId") long userId,
                                    @Param("quotaType") int quotaType,
                                    @Param("limitQuota") boolean limitQuota,
                                    @Param("totalQuota") BigDecimal totalQuota,
                                    @Param("consumeQuota") BigDecimal consumeQuota,
                                    @Param("totalAmount") BigDecimal totalAmount,
                                    @Param("disabled") boolean disabled);

    /**
     * 家庭付款:修改限额数据
     *
     * @param userId
     * @param amount
     * @return
     */
    int familyPay(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 家庭付款：退还限制额
     *
     * @param userId
     * @param amount
     * @return
     */
    int refundFamilyPay(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 家庭付款：修改主账号额度
     *
     * @param userId
     * @param amount
     * @return
     */
    int masterPay(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 家庭付款：退还主账号额度
     *
     * @param userId
     * @param amount
     * @return
     */
    int refundMasterPay(@Param("userId") long userId, @Param("amount") BigDecimal amount);


    /**
     * 获取企业付额度信息
     *
     * @param userId
     * @return
     */
    Data getCompanyPay(@Param("userId") long userId);

    /**
     * 企业付:变更限额数据
     *
     * @param userId
     * @param amount
     * @return
     */
    int updateUserQuota(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 企业付:退还限额
     *
     * @param userId
     * @param amount
     * @return
     */
    int refundUserQuota(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 更新企业余额
     *
     * @param companyId
     * @param amount
     * @return
     */
    int updateCompanyBalance(@Param("companyId") long companyId, @Param("amount") BigDecimal amount);

    /**
     * 退还企业余额
     *
     * @param companyId
     * @param amount
     * @return
     */
    int refundCompanyBalance(@Param("companyId") long companyId, @Param("amount") BigDecimal amount);


    /**
     * 给用户发放优惠券
     *
     * @param userCoupon
     * @return
     */
    int insertUserCoupon(UserCoupon userCoupon);

    /**
     * 更新优惠券的领取数量
     *
     * @param couponDefinitionId
     * @return
     */
    int gotCoupon(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 获取用户可用优惠券
     *
     * @param userId
     * @return
     */
    List<Data> getUseableUserCoupons(@Param("userId") long userId);

    /**
     * 获取单张优惠券数据
     *
     * @param couponId
     * @return
     */
    Data getUserCoupon(@Param("couponId") long couponId);

    /**
     * 获取失效的优惠券
     * @return
     */
    UserCoupon getInvalidUserCoupon();

    /**
     * 失效优惠券
     * @param couponId
     * @return
     */
    int invalidCoupon(@Param("couponId") long couponId);

    /**
     * 获取优惠券的定义信息
     *
     * @param couponDefinitionId
     * @return
     */
    CouponDefinition getCouponDefinition(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 使用优惠券
     *
     * @param couponId
     * @return
     */
    int useCoupon(@Param("couponId") long couponId);

    /**
     * 退还优惠券
     *
     * @param couponId
     * @return
     */
    int refundCoupon(@Param("couponId") long couponId);


    /**
     * 增加优惠券的使用
     *
     * @param couponDefinitionId
     * @return
     */
    int addCouponDefinitionUsed(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 减去优惠券的使用
     *
     * @param couponDefinitionId
     * @return
     */
    int minusCouponDefinitionUsed(@Param("couponDefinitionId") long couponDefinitionId);

    /**
     * 充值到账
     *
     * @param userId
     * @param rechargeId
     * @param tradeNo
     * @param actualAmount
     */
    int updateUserRecharge(@Param("userId") long userId, @Param("rechargeId") long rechargeId, @Param("tradeNo") String tradeNo, @Param("actualAmount") BigDecimal actualAmount);

    /**
     * 充值
     */
    int insertUserRecharge(UserRecharge userRecharge);

    /**
     * 查询用户充值记录
     */
    UserRecharge getUserRecharges(long rechargeId);

    /**
     * 插入资金流水
     *
     * @param userCapitalLogId
     * @param userId
     * @param amount
     * @param sourceType
     * @param sourceId
     * @param description
     * @return
     */
    int insertUserCapital(@Param("userCapitalLogId") long userCapitalLogId,
                          @Param("userId") long userId,
                          @Param("amount") BigDecimal amount,
                          @Param("sourceType") String sourceType,
                          @Param("sourceId") long sourceId,
                          @Param("description") String description);

    int insertCompanyCapitalLog(
            @Param("capitalLogId") long capitalLogId,
            @Param("companyId") long companyId,
            @Param("employeeId") long employeeId,
            @Param("amount") BigDecimal amount,
            @Param("isIn") int isIn,
            @Param("sourceType") String sourceType,
            @Param("sourceId") long sourceId,
            @Param("capitalLogType") int capitalLogType,
            @Param("detial") String detial);

    /**
     * 根据城市名称获取城市id
     *
     * @param areaName
     * @return
     */
    long getAreaId(@Param("areaName") String areaName);

    /**
     * 更换手机
     *
     * @param userId
     * @param loginNo
     * @return
     */
    int setLoginNo(@Param("userId") long userId, @Param("loginNo") String loginNo);

    /**
     * 设置／修改支付密码
     *
     * @param userId
     * @param payPassword
     * @return
     */
    int setPayPassword(@Param("userId") long userId, @Param("payPassword") String payPassword);

    /**
     * 修改支付密码
     *
     * @param userId
     * @param password
     * @return
     */
    int setLoginPassword(@Param("userId") long userId, @Param("password") String password);

    /**
     * 修改用户头像
     *
     * @param userId
     * @param portrait
     * @return
     */
    int updatePortrait(@Param("userId") long userId, @Param("portrait") String portrait);

    /**
     * 用户留言
     *
     * @param userId
     * @param leaveMessageId
     * @param leaveMessage
     * @return
     */
    int insertUserLeaveMessage(@Param("userId") long userId, @Param("leaveMessageId") long leaveMessageId, @Param("leaveMessage") String leaveMessage);

    /**
     * 获取openId
     * @param userId
     * @return
     */
    String getOpenId(@Param("userId") long userId);

    /**
     * 优惠券历史纪录
     * @param userId
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Data> getHistoryUserCoupons(@Param("userId") long userId,@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);

}
