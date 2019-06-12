package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.controller.JPush.JpushClientUtil;
import com.wufanbao.api.olddriverservice.entity.RequestInfo;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-08-21
 * Time:16:51
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 单点登陆
     */
    @RequestMapping(value = "employeeLogin", method = RequestMethod.POST)
    @ResponseBody
    public synchronized Object EmployeeLogin(String request) {
        System.out.println(request + "单点登录");
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        String mB = JSONObject.parseObject(requestInfo.getData()).getString("mB").toString();
        String registrationId = JSONObject.parseObject(requestInfo.getData()).getString("registrationId").toString();
        String tokens = "";
        try {
            tokens = JSONObject.parseObject(requestInfo.getData()).getString("tokens").toString();
        } catch (Exception e) {
            tokens = "";
        }
        ResponseInfo responseInfo = new ResponseInfo();
        long code = Long.valueOf(mB);
        String token = Long.toString(code, 16);
        Jedis jedis = jedisPool.getResource();
        Map map = new HashMap();
        try {
            if (tokens.equals("")) {
                if (jedis.get(mB) == null) {
                    jedis.set(mB, token);
                    jedis.set(token, registrationId);
                    map.put("token", token);
                    responseInfo.setData(map);
                    responseInfo.setCode(1);
                    responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                    return responseInfo;
                } else if (jedis.get(mB) != null) {
                    int kkk = onMessage(token, registrationId);
                    if (kkk == 0) {
                        jedis.set(mB, token);
                        jedis.set(token, registrationId);
                        map.put("token", token);
                        responseInfo.setData(map);
                        responseInfo.setCode(1);
                        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                        return responseInfo;
                    }
                }
            }
            if (tokens != null) {
                if (tokens.equals("1")) {
                    jedis.del(mB);
                    jedis.del(token);
                    map.put("token", "");
                    responseInfo.setData(map);
                    responseInfo.setCode(2321);
                    responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                    return responseInfo;
                }
                if (tokens.equals("2")) {
                    map.put("token", "");
                    responseInfo.setData(map);
                    responseInfo.setCode(2321);
                    responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                    return responseInfo;
                }
                if (jedis.get(mB).equals(tokens)) {
                    map.put("token", "");
                    responseInfo.setCode(1);
                    responseInfo.setData(map);
                    responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
                    return responseInfo;
                }
            }
        } catch (Exception e) {
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }

        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        responseInfo.setCode(2322);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    public int onMessage(String token, String registrationI) {
        Jedis jedis = jedisPool.getResource();
        String registrationId = jedis.get(token);
        if (registrationId == null || registrationId.equals(registrationI)) {
            return 0;
        }
        if (registrationId != registrationI) {
            String notification_title = "在别的手机登录";
            String extrasparam = "1";
            int jj = JpushClientUtil.sendToRegistrationId(registrationId, notification_title, extrasparam);
            System.out.println(jj);
            return 0;
        }
        return 1;
    }


}
