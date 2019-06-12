package com.wufanbao.api.oldclientservice.Tool;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * alphaWuFan
 * 拦截器
 *
 * @author Wang Zhiyuan
 * @date 2018-04-08 10:22
 **/
public class RequestValidation implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        RequestValidationCode methodAnnotation = method.getAnnotation(RequestValidationCode.class);
        Map ipAddressMap = getIpAddress(httpRequest);
        //ip
        String ip = ipAddressMap.get("ip").toString();
        String userAgent = ipAddressMap.get("userAgent").toString();
        //浏览器标识
        String browser = ipAddressMap.get("browser").toString();
        //系统
        String os = ipAddressMap.get("os").toString();
        //苹果手机标识
        String iphone = "MAC_OS_X_IPHONE";
        //安州手机标识
        String android = "ANDROID_MOBILE";
        //是否有注解
        if (methodAnnotation != null) {
            String mb = request.getParameter("Mb");
            //一个Ip 一个小时之内可以发送的次数
            int count = redisUtils.get(ip) == null ? 0 : Integer.valueOf(redisUtils.get(ip));
            //是不是安卓手机或者苹果手机
//            if (iphone.equals(os)||android.equals(os)){
            //ip出现的次数
            if (count > 10) {
                return false;
            } else {
                redisUtils.incr(ip);
                //一小时内一个Ip只能请求几次
                redisUtils.expire(ip, 3600);
            }
//            }else{
//                jedis.close();
//                return false;
//            }
        }

        return true;
    }

    /**
     * 过滤请求来源
     *
     * @param request
     * @return java.lang.Object
     * @author Wang Zhiyuan
     * @date 2018/4/4
     */
    public Map getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String userAgent = request.getHeader("User-Agent");
        UserAgent userAgent1 = UserAgent.parseUserAgentString(userAgent);
        Browser browser = userAgent1.getBrowser();
        OperatingSystem os = userAgent1.getOperatingSystem();
        Map map = new HashMap();
        map.put("ip", ip);
        map.put("userAgent", userAgent);
        map.put("browser", browser);
        map.put("os", os);
        return map;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
