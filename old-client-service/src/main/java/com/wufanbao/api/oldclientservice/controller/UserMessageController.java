package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("userMessage")
public class UserMessageController {
    @Autowired
    private UserMessageService userMessageService;

    /**
     * 读取用户的所有信息
     *
     * @param userId
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getAllUserMessage")
    public Object getAllUserMessage(long userId) {
        return userMessageService.getAllUserMessage(userId);
    }

    /**
     * 标记已读
     *
     * @param userId
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "markRead", method = RequestMethod.POST)
    public Object markRead(long userId) {
        return userMessageService.markRead(userId);
    }

    /**
     * 标记删除
     *
     * @param userId 用户id
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "markDelete", method = RequestMethod.POST)
    public Object markDelete(long userId) {
        return userMessageService.markDelete(userId);
    }
}
