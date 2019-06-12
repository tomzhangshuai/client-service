package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.service.RecommendedPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User:WangZhiyuan
 */
@Controller
@RequestMapping(value = "RecommendedPrize")
public class RecommendedPrizeController {
    @Autowired
    private RecommendedPrizeService recommendedPrizeService;

    /**
     * 推荐有奖,被邀请成功的用户信息
     *
     * @param userId 传递用户id，奖励类型;
     * @return
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getRecommendedPrize")
    public Object getRecommendedPrize(long userId) {
        return recommendedPrizeService.getRecommendedPrize(userId);
    }
}
