package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.BaseController;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.service.UserGrowUpAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping(value = "userGrowUpAward", method = RequestMethod.POST)
public class UserGrowUpAwardController extends BaseController {
    @Autowired
    private UserGrowUpAwardService userGrowUpAwardService;

    /**
     * 用户签到
     *
     * @param userId
     * @return
     * @eidtor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "userSignIn", method = RequestMethod.POST)
    public Object userSignIn(long userId) {
        ResponseInfo responseInfo = userGrowUpAwardService.userSignIn(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * @Description: 积分商城商品
     * @Param: []
     * @return: java.lang.Object
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "integralCommodity", method = RequestMethod.POST)
    public Object integralCommodity() {
        ResponseInfo responseInfo = userGrowUpAwardService.integralCommodity();
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * @Description: 全部用户兑换信息
     * @Param: []
     * @return: java.lang.Object
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "allIntegralExchange", method = RequestMethod.POST)
    public Object allIntegralExchange() {
        ResponseInfo responseInfo = userGrowUpAwardService.allIntegralExchange();
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * @Description: 用户兑换信息
     * @Param: [userId]
     * @return: java.lang.Object
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/26
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "userIntegralExchange", method = RequestMethod.POST)
    public Object userIntegralExchange(long userId) {
        ResponseInfo responseInfo = userGrowUpAwardService.userIntegralExchange(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * @Description: 兑换积分商城商品
     * @Param: [userId, integralCommodityId, amount, quantity]
     * @return: java.lang.Object
     * @Author:Wang Zhiyuan
     * @Date: 2018/2/28
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "exchangeCommodity", method = RequestMethod.POST)
    public Object exchangeCommodity(long userId, long integralCommodityId, BigDecimal amount, int quantity) {
        ResponseInfo responseInfo = userGrowUpAwardService.exchangeCommodity(userId, integralCommodityId, amount, quantity);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 抽奖
     *
     * @param userId
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/5/16
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "lottery", method = RequestMethod.POST)
    public Object lottery(long userId) {
        ResponseInfo responseInfo = userGrowUpAwardService.lottery(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 抽奖次数
     *
     * @param userId
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/5/16
     * @editor zhaojing
     */
    @ResponseBody
    @RequestMapping(value = "residueDegree", method = RequestMethod.POST)
    public Object residueDegree(long userId) {
        ResponseInfo responseInfo = userGrowUpAwardService.residueDegree(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }

    /**
     * 用户签到信息
     *
     * @param userId
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getUserSignIn", method = RequestMethod.POST)
    public Object getUserSignIn(long userId) {
        ResponseInfo responseInfo = userGrowUpAwardService.getUserSignIn(userId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }


    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "scanAQRCode", method = RequestMethod.POST)
    public Object scanAQRCode(long userId, String tempId) {
        ResponseInfo responseInfo = userGrowUpAwardService.scanAQRCode(userId, tempId);
        return retContent(responseInfo.getCode(), responseInfo.getData());
    }


    @ResponseBody
    @RequestMapping(value = "resetTheNumberOfDraws", method = RequestMethod.POST)
    public String resetTheNumberOfDraws(String str) {
        return userGrowUpAwardService.resetTheNumberOfDraws(str);
    }
}
