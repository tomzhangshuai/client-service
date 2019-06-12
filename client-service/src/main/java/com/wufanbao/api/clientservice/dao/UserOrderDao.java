package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Repository
public interface UserOrderDao {
    /**
     * 用户订单的添加
     */
    int insertUserOrder(UserOrder userOrder);

    /**
     * 取消订单
     *
     * @param userOrderId
     * @return
     */
    int cancelUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 支付订单
     *
     * @param userOrderId
     * @return
     */
    int payUserOrder(@Param("userOrderId") long userOrderId, @Param("payType") int payType, @Param("invalidTime") Date invalidTime);

    /**
     * 支付订单
     *
     * @param userOrderId
     * @param payType
     * @param receiveAmount
     * @return
     */
    int notifyPayUserOrder(@Param("userOrderId") long userOrderId, @Param("payType") int payType, @Param("invalidTime") Date invalidTime, @Param("receiveAmount") BigDecimal receiveAmount);

    /**
     * status=0 支付订单
     * @param userOrderId
     * @param payType
     * @param invalidTime
     * @param receiveAmount
     * @return
     */
    /**
     * 更改订单来源
     * @return
     */
    int updateOriginateId(@Param("originateId") int originateId,@Param("userOrderId") long userOrderId);

    int updateMessageStatus(@Param("userOrderId") long  userOrderId);
    /**
     *
     * @param userOrderId
     * @param payType
     * @param invalidTime
     * @param receiveAmount
     * @return
     */
    int notifyPayUserOrderInvalid(@Param("userOrderId") long userOrderId, @Param("payType") int payType, @Param("invalidTime") Date invalidTime, @Param("receiveAmount") BigDecimal receiveAmount);
    /**
     * 订单交易明显
     *
     * @param userOrderId
     * @param capitalType
     * @param sourceType
     * @param sourceId
     * @param amount
     * @param description
     * @return
     */
    int insertUserOrderCapital(@Param("userOrderId") long userOrderId,
                               @Param("capitalType") int capitalType,
                               @Param("sourceType") String sourceType,
                               @Param("sourceId") String sourceId,
                               @Param("amount") BigDecimal amount,
                               @Param("description") String description);

    /**
     * 订单退款交易明显变更
     *
     * @param userOrderId
     * @param capitalType
     * @param refundAmount
     * @return
     */
    int refundUserOrderCapital(@Param("userOrderId") long userOrderId,
                               @Param("capitalType") int capitalType,
                               @Param("refundAmount") BigDecimal refundAmount);

    /**
     * 评价订单
     *
     * @param userOrderId
     * @return
     */
    int assessUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 评价订单行
     *
     * @param userOrderLine
     * @return
     */
    int assessUserOrderLine(UserOrderLine userOrderLine);

    /**
     * 订单失效
     *
     * @param userOrderId
     * @return
     */
    int invalidUserOrder(@Param("userOrderId") long userOrderId);


    /**
     * 退款中
     *
     * @param userOrderId
     * @return
     */
    int refundUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * status=0；失效订单的退款
     * @param userOrderId
     * @param payType
     * @return
     */
    int refundUserOrderinvalid(@Param("userOrderId") long userOrderId,@Param("payType") int payType);

    /**
     * 已退款
     * @param userOrderId
     * @return
     */

    int refundedUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 添加订单行
     *
     * @param userOrderLine
     * @return
     */
    int insertUserOrderLine(UserOrderLine userOrderLine);
    /**
     * 获取即将超时订单
     * @param
     * @return
     */
    List<Data> getOverdueUserOrder();

    /**
     * 获取支付订单来源
     * @param userOrderId
     * @return
     */
    Data getMessage(@Param("userOrderId") long userOrderId);

    /**
     * 获取用户订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> getUserOrders(@Param("userId") long userId, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

    /**
     * 获取待付款订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> getPayUserOrders(@Param("userId") long userId, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

    /**
     * 获取待取餐订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> getPaidUserOrders(@Param("userId") long userId, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

    /**
     * 获取待评价订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> getAssessUserOrders(@Param("userId") long userId, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

    /**
     * 获取退款／已失效订单
     *
     * @param userId
     * @return
     */
    List<UserOrder> getInvalidUserOrders(@Param("userId") long userId, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

    /**
     * 获取失效订单
     *
     * @return
     */
    List<UserOrder> getInvalidUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 获取制作中的用户订单
     * @param userOrderId
     * @return
     */
    List<UserOrder> getMakingUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 获取订单数据
     *
     * @param userOrderId
     * @return
     */
    UserOrder getUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 更改状态
     * @param userOrderId
     * @return
     */
    int updateStatus(@Param("userOrderId") long userOrderId);

    /**
     * 判断用户下是否还有未完成的订单
     *
     * @param userId
     * @return
     */
    int exitUnCompleteUserOrder(@Param("userId") long userId);

    /**
     * 获取机器里的用户可以取餐订单
     *
     * @param userId
     * @param machineId
     * @return
     */
    List<UserOrder> getTakeUserOrders(@Param("userId") long userId, @Param("machineId") long machineId);

    /**
     * 获取最早的待支付订单
     *
     * @return
     */
    List<UserOrder> getToPayUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 获取订单数据
     *
     * @param userOrderId
     * @return
     */
    Data getUserOrderDetail(@Param("userOrderId") long userOrderId);


    /**
     * 获取订单行数据
     *
     * @return
     */
    List<UserOrderLine> getUserOrderLines(@Param("userOrderId") long userOrderId);

    /**
     * 获取订单行数据（包含商品数据）
     *
     * @param userOrderId
     * @return
     */
    List<Data> getUserOrderLineAndProducts(@Param("userOrderId") long userOrderId);

    /**
     * 获取订单行数据（包含商品数据和评价详情）
     *
     * @param userOrderId
     * @return
     */
    List<Data> getAssessUserOrderLineAndProducts(@Param("userOrderId") long userOrderId);


    /**
     * 添加入，商品出餐制作表
     *
     * @param userOrderProductLine
     * @return
     */
    int inserUserOrderProductLine(UserOrderProductLine userOrderProductLine);

    /**
     * 获取取餐记录
     *
     * @param userOrderId
     * @return
     */
    List<Data> getUserOrderProductLines(@Param("userOrderId") long userOrderId);

    /**
     * 获取单条取餐记录
     *
     * @param userOrderProductLineId
     * @return
     */
    Data getUserOrderProductLine(@Param("userOrderProductLineId") long userOrderProductLineId);

    /**
     * 获取分享图片
     *
     * @return
     */
    ImagesShare getImagesShare();

    UserOrder findUserOrder(@Param("userOrderId") long userOrderId);

    /**
     * 根据订单id获取手机号
    * @param userOrderId
     * @return
     */
    String getUserMb(@Param("userOrderId") long userOrderId);

}
