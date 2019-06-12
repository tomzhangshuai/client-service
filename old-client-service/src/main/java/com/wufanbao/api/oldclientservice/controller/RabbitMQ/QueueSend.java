package com.wufanbao.api.oldclientservice.controller.RabbitMQ;

import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * User:Wangshihao
 * Date:2017-10-10
 * Time:9:57
 */
@Controller
@RequestMapping("QueueSend")
public class QueueSend {
    @Autowired
    private RedisUtils redisUtils;

    public void Registration(String userId, String registrationId) {
        try {
            redisUtils.set(userId + ",1", registrationId);
        } catch (Exception e) {
            System.out.println(e.toString());
            Logger.getLogger(String.valueOf(e));
        }
    }

    @RequestMapping("jjj")
    @ResponseBody
    public Object jjj(String userId) {
        String sss = null;
        try {
            sss = redisUtils.get(userId + ",1");
        } catch (Exception e) {
            Logger.getLogger(String.valueOf(e));
        }
        return sss;
    }
}
