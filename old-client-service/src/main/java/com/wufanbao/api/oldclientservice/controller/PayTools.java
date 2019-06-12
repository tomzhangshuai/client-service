package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * User:Wangshihao
 * Date:2017-10-25
 * Time:14:37
 */
public class PayTools {
    /**
     * 插入用户资金流水
     *
     * @param userId
     * @param total_fee
     * @param newBalance
     * @return
     */
    public UserCapitalLog userMoney(long userId, double total_fee, double newBalance, String context, long userOrderId) {
        UserCapitalLog userCapitalLog = new UserCapitalLog();
        long userCapitalLogId = IDGenerator.generateById("userCapitalLogId", userId);
        userCapitalLog.setUserCapitalLogId(userCapitalLogId);
        userCapitalLog.setUserId(userId);
        userCapitalLog.setAmount(total_fee);
        userCapitalLog.setSourceType("userOrder");
        userCapitalLog.setSourceId(userOrderId);
        userCapitalLog.setDescription(context);
        userCapitalLog.setBalance(newBalance);
        return userCapitalLog;
    }

    /**
     * 插入充值表
     *
     * @param trade_no
     * @param total_fee
     * @param rechargeId
     * @param userId
     * @return
     */
    public UserRecharge userPay(String trade_no, double total_fee, long rechargeId, long userId) {
        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setTradeNo(trade_no);
        userRecharge.setPayStatus(2);
        userRecharge.setActualAmount(total_fee);
        userRecharge.setRechargeId(rechargeId);
        userRecharge.setUserId(userId);
        return userRecharge;
    }

    /**
     * 修改用户余额
     *
     * @param userId
     * @param newBalance
     */
    public UserRegistered updateBalance(long userId, double newBalance) {
        UserRegistered userRegistered = new UserRegistered();
        userRegistered.setUserId(userId);
        userRegistered.setBalance(newBalance);
        userRegistered.setUsableBalance(newBalance);
        return userRegistered;
    }

    /**
     * 修改订单状态付款金额
     *
     * @param userOrderId
     * @param total_fee
     * @param payType
     * @return
     */
    public UserOrder updateUserOrder(long userOrderId, double total_fee, int payType) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserOrderId(userOrderId);
        userOrder.setActualAmount(total_fee);
        userOrder.setReceiveAmount(total_fee);
        userOrder.setPayType(payType);
        userOrder.setStatus(3);
        Timestamp invalidTime = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(invalidTime);
        calendar.add(Calendar.HOUR_OF_DAY, 36);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ts = simpleDateFormat.format(calendar.getTime());
        invalidTime = Timestamp.valueOf(ts);
        System.out.println(">>>>>失效时间" + invalidTime);
        userOrder.setInvalidTime(invalidTime);
        //userOrderService.updatePay(userOrder);
        return userOrder;
    }
}
