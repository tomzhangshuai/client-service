
package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * User:WangZhiyuan
 */
public interface UserOrderDao {
    /**
     * 添加
     *
     * @param userOrder
     * @return
     */
    int insertUserOrder(UserOrder userOrder);


    /**
     * 根据用户ID查询所有未开发票的年放到list
     *
     * @param userCapitalLogParameter
     * @return
     */
    List<UserCapitalLogParameter> getYear(UserCapitalLogParameter userCapitalLogParameter);

    /**
     * 根据用户id去查所有的月
     *
     * @param userCapitalLogParameter
     * @return
     */
    List<UserCapitalLogParameter> getMonth(UserCapitalLogParameter userCapitalLogParameter);

    /**
     * 根据用户id和所有的月去查询每日的订单信息
     *
     * @param userCapitalLogParameter
     * @return
     */
    List<UserOrder> getMonthInfo(UserCapitalLogParameter userCapitalLogParameter);

    /**
     * 根据订单编号去更改订单是否开发票
     *
     * @param orderid
     * @return
     */
    int updateOrder(List orderid);

    /**
     * 根据订单编号去更改订单是否开发票一单
     *
     * @param vo
     * @return
     */
    int updateOrder1(UserOrder vo);

    /**
     * 根据用户id去查询用户待取餐订单信息
     *
     * @param userId
     * @return
     */
    List<OrderInfo> getOrderInfo(@Param("userId") long userId);

    /**
     * 根据用户id去查询用户所有的订单
     *
     * @param userId
     * @return
     */
    List<OrderInfo> getAllOrderInfo(@Param("userId") long userId);

    /**
     * 根据订单id去查询订单信息
     *
     * @param orderId
     * @return
     */
    List<OrderInfo> getOrderInfoByOrderId(@Param("orderId") long orderId);

    /**
     * 获取用户支付时间
     *
     * @param userOrderId
     * @return
     */
    OrderInfo getPayTime(@Param("userOrderId") long userOrderId);

    /**
     * 根据订单id去查询用户订单内的商品信息
     *
     * @param orderId
     * @return
     */
    List<ProductOnline> getProductOnline(@Param("orderId") long orderId);

    /**
     * 判断是否有交易完成的用户订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> judgeOrder(@Param("userId") long userId);

    /**
     * 作废掉过期未取餐的订单
     *
     * @return
     */
    int invalidUserOrder(@Param("userOrderId") long userOrderId);

    UserOrder getExpireUserOrder();

    List<UserOrderLine> getUserOrderLines(@Param("userOrderId") long userOrderId);

    /**
     * User:Wangshihao
     * Date:2017-08-09
     * Time:14:24
     */
    /**
     * 取消订单
     */
    public void cancelOrder(UserOrder userOrder);

    /**
     * 支付
     */
    public void updatePay(UserOrder userOrder);

    /**
     * 取消订单退钱
     */
    int cancelRefund(@Param("userOrderId") long userOrderId, @Param("status") int status);

    /**
     * 超过30分钟不能退款
     */
    public TimeOut qutrytimeOut(@Param("userId") long userId, @Param("userOrderId") long userOrderId);

    /**
     * 用户订单评价
     *
     * @param userOrder
     * @return
     */
    int userEvaluate(UserOrder userOrder);

    /**
     * 用户订单评价详细
     *
     * @param userOrderLine
     * @return
     */
    int userEvaluateLine(UserOrderLine userOrderLine);

    /**
     * 查询过期时间
     *
     * @param userOrderId
     * @return
     */
    public UserOrder queryInvalidTime(long userOrderId);

    /**
     * 查询订单状态
     *
     * @param userOrderId
     * @return
     */
    public UserOrder queryUserOrderStatus(long userOrderId);

    /**
     * 查询机器非食用物品
     *
     * @param machineId
     * @return
     */
    public List<ProductDetails> queryProduct(long machineId);

    /**
     * 商品详情
     *
     * @param machineId
     * @param productGlobalId
     * @return
     */
    public List<ProductDetails> queryDetails(@Param("machineId") long machineId, @Param("productGlobalId") long productGlobalId);

    /**
     * 根据机器号获取机位图
     *
     * @param machineId
     * @return
     */
    MachineLocation getMachineLocation(@Param("machineId") long machineId);

    /**
     * 插入用户奖励
     *
     * @param integralLogId
     * @param userId
     * @param quantity
     * @param sourceType
     * @param sourceTypeId
     * @param createTime
     * @param description
     * @return
     */
    int awardIntegral(@Param("integralLogId") long integralLogId,
                      @Param("userId") long userId,
                      @Param("quantity") BigDecimal quantity,
                      @Param("sourceType") String sourceType,
                      @Param("sourceTypeId") long sourceTypeId,
                      @Param("createTime") Date createTime,
                      @Param("description") String description);

    /**
     * 插入用户奖励表记录
     *
     * @param rewardId
     * @param userId
     * @param sourceType
     * @param sourceId
     * @param createTime
     * @param rewardValue
     * @param rewardType
     * @param reward
     * @return
     */
    int awardLog(@Param("rewardId") long rewardId,
                 @Param("userId") long userId,
                 @Param("sourceType") String sourceType,
                 @Param("sourceId") long sourceId,
                 @Param("createTime") Date createTime,
                 @Param("rewardValue") BigDecimal rewardValue,
                 @Param("rewardType") int rewardType,
                 @Param("reward") String reward);

    /**
     * 更新用户积分
     *
     * @param userId
     * @param integral
     * @return
     */
    int updateUserIntegral(@Param("userId") long userId, @Param("integral") BigDecimal integral);

    /**
     * 查询是否已经发放过奖励
     *
     * @param userOrderId
     * @return
     */
    UserReward getUserIdByOrderId(@Param("userOrderId") long userOrderId);

    ImagesShare randShowImage();

    /**
     * 根据用户id判断用户企业额度
     *
     * @param userId
     * @return
     */
    UserQuota getUserQuotaInfo(@Param("userId") long userId);

    int insertCompanyCapitalLog(
            @Param("capitalLogId") long capitalLogId,
            @Param("companyId") long companyId,
            @Param("employeeId") long employeeId,
            @Param("amount") BigDecimal amount,
            @Param("isIn") int isIn,
            @Param("sourceType") String sourceType,
            @Param("sourceId") String sourceId,
            @Param("capitalLogType") int capitalLogType,
            @Param("detial") String detial);

    /**
     * 更新企业余额
     *
     * @param companyId
     * @param
     * @return
     */
    int updateCompanyBalance(@Param("companyId") long companyId, @Param("amount") BigDecimal amount);

    /**
     * 更新员工用户的企业余额
     *
     * @param userId
     * @param amount
     * @return
     */
    int updateUserQuota(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 更改用户订单状态
     *
     * @param userOrderId
     * @return
     */
    int updateUserOrderType(@Param("userOrderId") long userOrderId,
                            @Param("status") int status,
                            @Param("actualAmount") BigDecimal actualAmount,
                            @Param("receiveAmount") BigDecimal receiveAmount,
                            @Param("payTime") Date payTime,
                            @Param("payType") int payType
    );

    /**
     * 判断是否是企业用户
     *
     * @param userId
     * @return
     */
    int getCompanyPayment(@Param("userId") long userId);

    /**
     * 搜索订单企业付金额
     *
     * @param userOrderId
     * @return
     */
    CompanyCapitalLog getCompanyPayAmount(@Param("userOrderId") long userOrderId);

    /**
     * 退还企业金额
     *
     * @param companyId
     * @return
     */
    int refundEnterprise(@Param("companyId") long companyId, @Param("amount") BigDecimal amount);

    /**
     * 退钱给用户
     *
     * @param userId
     * @param amount
     * @return
     */
    int refundUser(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 根据订单id去查询订单信息
     *
     * @param userOrderId
     * @return
     */
    UserOrder orderType(@Param("userOrderId") long userOrderId);

    /**
     * 查询用餐详情
     *
     * @param userOrderId
     * @return
     */
    List<UserOrderProductLine> orderLineType(@Param("userOrderId") long userOrderId);

    //用户退款
    int userRefund(@Param("userId") long userId, @Param("balance") BigDecimal refundAmount);

    Date selectTime();

    BigDecimal ssssss(@Param("userId") long userId);

    /**
     * 机器是否能够退款
     *
     * @param machineId
     * @return
     */
    int machineType(@Param("machineId") long machineId);

    /**
     * 可退商品集合
     *
     * @param userOrderId
     * @return
     */
    List<UserOrderProductLine> refundOrderLine(@Param("userOrderId") long userOrderId);

    /**
     * 查看用户查看发现新闻的积分是否发放
     *
     * @param userId
     * @return
     */
    UserCapitalLog getUserFindAward(@Param("userId") long userId, @Param("sourceType") String sourceType, @Param("sourceTypeId") long sourceId);

    /**
     * 根据机器id获取机器上商品的价格
     *
     * @param machineId
     * @return
     */
    List<Map> getProduct(@Param("machineId") long machineId);

    /**
     * 根据机器ID和用户Id查询用户订单
     *
     * @param machineId 机器ID
     * @param userId    用户ID
     * @return java.util.List<java.util.Map>
     * @date 2018/6/22
     */
    List<Map> getUserOrderInfoByMachineIdAndUserId(@Param("machineId") Long machineId, @Param("userId") long userId);

    Map getUserOrderInfoByMachineIdAndUserOrderId(@Param("machineId") Long machineId, @Param("userOrderId") long userOrderId);

    /**
     * 根据用户订单ID查询用户评价
     *
     * @param userOrderId 用户订单ID
     * @return java.util.List<java.util.Map>
     * @date 2018/6/22
     */
    List<Map> getOrderEvaluationByUserOrderId(@Param("userOrderId") long userOrderId);

    /**
     * 查询用户某一个订单信息
     *
     * @param userId      用户ID
     * @param userOrderId 用户订单ID
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/4/27
     */
    Map inquireUserOrderInfoByUserOrderId(@Param("userId") long userId, @Param("userOrderId") long userOrderId);

    /**
     * 根据用户Id查询用户家庭付订单
     *
     * @param userId    用户ID
     * @param sonUserId 子账户用户ID
     * @return java.util.List<java.util.Map>
     * @date 2018/7/2
     */
    List<Map> familyPayOrderByUserId(@Param("userId") long userId, @Param("sonUserId") long sonUserId);

    /**
     * 用户取餐订单详情
     *
     * @param userId      用户ID
     * @param userOrderId 用户订单ID
     * @return java.util.Map
     * @date 2018/7/2
     */
    Map takeFoodUserOrderInfo(@Param("userId") long userId, @Param("userOrderId") long userOrderId);

    /**
     * 退款详情
     *
     * @param userOrderId
     * @return java.util.List<java.util.Map>
     * @date 2018/7/2
     */
    List<Map> returnUserOrderLineInfo(@Param("userOrderId") long userOrderId);

    BigDecimal receiveAmount(@Param("userOrderId") long userOrderId);

    /**
     * 根据订单信息获取用户信息
     *
     * @param userOrderId 用户订单ID
     * @return java.util.Map
     * @date 2018/7/3
     */
    Map getUserOrderInfoByOrderId(@Param("userOrderId") long userOrderId);

    /**
     * 分享订单信息
     *
     * @param userOrderId 用户订单ID
     * @return java.util.Map
     * @date 2018/7/4
     */
    Map showOrderInfo(@Param("userOrderId") long userOrderId);


}
