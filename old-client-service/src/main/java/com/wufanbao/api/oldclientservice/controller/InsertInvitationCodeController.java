package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.service.InsertInvitationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 积分记录
 *
 * @author Wang Zhiyuan
 */
@Controller
@RequestMapping(value = "InsertInvitationCodeController", method = RequestMethod.POST)
public class InsertInvitationCodeController {
    @Autowired
    private InsertInvitationCodeService insertInvitationCodeService;

    /**
     * 用户邀请信息记录
     *
     * @param userId 用户ID
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "rewardRecord", method = RequestMethod.POST)
    public Map rewardRecord(long userId) {
        return insertInvitationCodeService.rewardRecord(userId);
    }

    /**
     * 用户邀请奖励轮播
     *
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "rewardRotation", method = RequestMethod.POST)
    public Map rewardRotation() {
        return insertInvitationCodeService.rewardRotation();
    }


    /**
     * 验证验证码是否正确
     *
     * @param invitationCode 邀请人的邀请码
     * @param userId         被邀请人的用户ID
     * @return 返回插入结果
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "InsertInvitationCode", method = RequestMethod.POST)
    public Object insertInvitationCode(String invitationCode, long userId) {
        return insertInvitationCodeService.InsertInvitationCode(invitationCode, userId);
    }

}
