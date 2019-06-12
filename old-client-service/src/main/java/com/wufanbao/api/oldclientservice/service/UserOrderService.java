package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.*;

import org.apache.ibatis.annotations.Param;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * user:WangZhiyuan
 */
public interface UserOrderService {
    //根据用户ID查询所有未开发票的年放到list
    //List<UserCapitalLogParameter> getYear(UserCapitalLogParameter userCapitalLogParameter);

    //根据用户id去查所有的月
    //List<UserCapitalLogParameter> getMonth(UserCapitalLogParameter userCapitalLogParameter);

    //根据用户id和所有的月去查询每日的订单信息
    //List<UserOrder> getMonthInfo(UserCapitalLogParameter userCapitalLogParameter);

    //根据订单编号去更改订单是否开发票
    //int updateOrder(List orderid);

    //根据订单编号去更改订单是否开发票一单
    //int updateOrder1(UserOrder vo);

    //根据订单id去查询用户订单内的商品信息
    List<ProductOnline> getProductOnline(long orderId);

    //根据用户id去查询用户订单信息
    List<OrderInfo> getOrderInfo(@Param("userId") long userId);


    //根据订单id去查询订单信息
    List<OrderInfo> getOrderInfoByOrderId(long orderId);

    /**
     * 根据机器号获取机位图
     *
     * @param machineId
     * @return
     */
    Map getMachineLocation(@Param("machineId") long machineId);

    /**
     * 作废掉过期未取餐的订单
     *
     * @return
     */
    void cancellationOrder() throws Exception;

    /**
     * 根据用户id去查询用户所有的订单
     *
     * @param userId
     * @return
     */
    List<OrderInfo> getAllOrderInfo(@Param("userId") long userId);

    /**
     * 判断是否有交易完成的用户订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> judgeOrder(long userId);
    /**
     * User:Wangshihao
     * Date:2017-08-09
     * Time:14:34
     */
    /**
     * 取消订单
     */
    Map cancelOrder(long userOrderId, long userId, long couponId) throws IOException, TimeoutException;

    /**
     * 取消订单退钱
     */
    int cancelRefund(long userOrderId, int status);

    /**
     * 超过30分钟不能退款
     */
    public TimeOut qutrytimeOut(@Param("userId") long userId, @Param("userOrderId") long userOrderId);

    /**
     * 用戶評價
     *
     * @param userOrder
     * @param UserOrderLine
     * @return
     */
    Map userEvaluate(UserOrder userOrder, String UserOrderLine);

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
    public List<ProductDetails> queryDetails(long machineId, long productGlobalId);

    /**
     * 用户奖励
     *
     * @param userId
     * @return
     */
    public Map userAward(long userId, long userOrderId);

    /**
     * 随机展示一个图片
     *
     * @return
     */
    public Map randShowImage(long userId, long userOrderId);

    /**
     * 根据用户id获取用户企业额度
     *
     * @return
     */
    public Map getUserQuotaInfo(long userId, BigDecimal amount, long userOrderId);

    /**
     * 企业付订单退款
     *
     * @param userId
     * @param userOrderId
     * @return
     */
    boolean refundEnterprise(long userId, long userOrderId);

    Map refundUser(long userId, long userOrderId) throws Exception;

    /**
     * 数据库时间
     *
     * @return
     */
    Date selectTime();

    /**
     * 查看机器状态
     *
     * @param machineId
     * @return
     */
    int machineType(long machineId);

    ResponseInfo getUserFindAward(long userId, long sourceTypeId);

    /**
     * 添加订单
     *
     * @param userOrder
     * @param productGlobalList
     * @param couponDefinitionId
     * @param appointTime
     * @return
     * @throws Exception
     * @editor zhaojing
     */
    Object insertUserOrder(UserOrder userOrder, String productGlobalList, long couponDefinitionId, String appointTime) throws Exception;

    //支付订单
    Map orderPay(long userOrderId, int payType, long userId, double amount, String payPassword);

    boolean refundFamily(long userId, long userOrderId);

    /**
     * 用户订单详情
     *
     * @param userOrderId 订单ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/6/22
     */
    ResponseInfo userOrderEvaluation(long userOrderId);

    /**
     * 根据用户ID查询用户家庭付订单
     *
     * @param userId    主用户ID
     * @param sonUserId 子账ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/7/2
     */
    ResponseInfo familyPayOrderByUserId(long userId, long sonUserId);

    /**
     * 取餐
     *
     * @param userId
     * @param userOrderId
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/7/3
     */
    ResponseInfo takeFood(long userId, long userOrderId) throws Exception;

    /**
     * 退款详情
     *
     * @param userOrderId
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/7/2
     */
    ResponseInfo returnUserOrderLineInfo(long userOrderId);

    /**
     * 展示的订单信息
     *
     * @param userOrderId 用户订单ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @date 2018/7/4
     */
    ResponseInfo showOrderInfo(long userOrderId);

    //void ss(long machineId, long userOrderId) throws Exception;


}

