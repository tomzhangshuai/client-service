package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

public interface UserGrowUpAwardService {
    /**
     * @Description: 用户签到
     * @Param: [userId]
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date:
     */
    ResponseInfo userSignIn(long userId);

    /**
     * @Description: 用户签到信息
     * @Param: [userId]
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    ResponseInfo getUserSignIn(long userId);

    /**
     * @Description: 全部用户兑换记录
     * @Param: []
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    ResponseInfo allIntegralExchange();

    /**
     * @Description: 用户兑换记录
     * @Param: [userId]
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    ResponseInfo userIntegralExchange(long userId);

    /**
     * @Description: 积分商城商品
     * @Param: []
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    ResponseInfo integralCommodity();

    /**
     * @Description: 兑换积分商城商品
     * @Param: [userId, integralCommodityId, amount, quantity]
     * @return: com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     */
    ResponseInfo exchangeCommodity(long userId, long integralCommodityId, BigDecimal amount, int quantity);

    /**
     * 扫描二维码
     *
     * @param userId 用户ID
     * @param tempId 二维码Id
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/3/21
     */
    ResponseInfo scanAQRCode(long userId, String tempId);

    /**
     * 用户抽奖
     *
     * @param userId 用户Id
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/16
     */
    ResponseInfo lottery(long userId);

    /**
     * 剩余次数
     *
     * @param userId 用户ID
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/5/16
     */
    ResponseInfo residueDegree(long userId);

    String resetTheNumberOfDraws(String str);

}
