package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.Tool.RandomNum;
import com.wufanbao.api.oldclientservice.Tool.RedisUtils;
import com.wufanbao.api.oldclientservice.controller.SMSInterface;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: alphaWuFan
 * @description: 验证码
 * @author: Wang Zhiyuan
 * @create: 2018-03-28 14:42
 **/
public class VerificationCodeServiceImpl implements VerificationCodeService {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Object sendVerificationCode(String Mb) throws IOException {
        //不同的接口
        int num = 6;
        //一个手机号五分钟只内只能发多少次
        int number = 3;
        Map map = new HashMap(16);
        //生成验证码
        RandomNum randomNum = new RandomNum();
        String code = randomNum.random(4);
        //获取手机号
        String str = Long.toString(Long.parseLong(Mb), 12) + num;
        SMSInterface smsInterface = new SMSInterface();
        try {
            //手机号发送次数
            int count = Integer.parseInt(redisUtils.get(str));
            //如果小于三次
            if (count < number) {
                //如果不用为空
                if (redisUtils.get(str) != null) {
                    //数值加一
                    redisUtils.incr(str);
                    //将手机号和验证码放到里面
                    redisUtils.set(Mb, code);
                    //五分钟后失效
                    redisUtils.expire(Mb, 300);
                    //发送验证吗
                    Object status = smsInterface.SMS(Mb, code);
                    System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                    map.put("succeed", 1);
                    return map;
                }
            } else if (count == number) {
                map.put("succeed", 2);
                return map;
            }
        } catch (Exception e) {
            if (redisUtils.get(str) == null) {
                redisUtils.incr(str);
                redisUtils.expire(str, 300);
                redisUtils.set(Mb, code);
                redisUtils.expire(Mb, 300);
                Object status = smsInterface.SMS(Mb, code);
                System.out.println(">>>>>>>>>>>>>>>>>>状态" + status);
                map.put("succeed", 1);
                return map;
            }
        }
        map.put("succeed", 1);
        return map;
    }
}
