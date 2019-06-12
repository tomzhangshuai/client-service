package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.Alipay;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;

import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-14
 * Time:10:13
 */
public interface AlipayService {
    public List<Alipay> queryAlipay(long userOrderId);

    /**
     * 支付宝H5支付
     *
     * @param userId      用户Id
     * @param userOrderId 用户OrderId
     * @return com.wufanbao.api.oldclientservice.tool.ResponseInfo
     * @date 2018/5/7
     */
    ResponseInfo orderPayForH5(long userId, long userOrderId);

    /**
     * 验证订单支付信息
     *
     * @param userId      用户ID
     * @param userOrderId 用户订单ID
     * @return java.util.Map<java.lang.String   ,   java.lang.String>
     * @author Wang Zhiyuan
     * @date 2018/5/7
     */
    Map<String, String> verifyOrderPayInfo(long userId, long userOrderId);
}
