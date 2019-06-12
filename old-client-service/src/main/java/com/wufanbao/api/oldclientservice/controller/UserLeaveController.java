package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.Userleavemessage;
import com.wufanbao.api.oldclientservice.service.UserLeaveService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:15:45
 */
@Controller
@RequestMapping("userleave")
public class UserLeaveController {

    @Autowired
    private UserLeaveService userLeaveService;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserLeaveController.class);

    /**
     * 留言
     *
     * @param request
     * @return
     * @editor zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "adduserleave", method = RequestMethod.POST)
    @ResponseBody
    public Object addUserleave(HttpServletRequest request) {
        Map map = new HashMap();
        try {
            Long userId = Long.parseLong(request.getParameter("userId"));
            String leaveMessage = request.getParameter("leavemessage");
            Userleavemessage userleavemessage = new Userleavemessage();
            long messageId = IDGenerator.generateById("messageId", userId);
            userleavemessage.setLeaveMessageId(messageId);
            userleavemessage.setUserId(userId);
            userleavemessage.setLeaveMessage(leaveMessage);
            userLeaveService.addleave(userleavemessage);
            map.put("return", 0);
        } catch (Exception e) {
            map.put("return", 1);
            logger.info(e.toString());
            throw new RuntimeException();
        }
        return map;
    }

}
