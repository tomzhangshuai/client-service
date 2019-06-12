package com.wufanbao.api.clientservice.filter;

import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.config.Audience;
import com.wufanbao.api.clientservice.service.ApiServiceException;
import com.wufanbao.api.clientservice.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：
 * iss(Issuser)：代表这个JWT的签发主体；
 * sub(Subject)：代表这个JWT的主体，即它的所有人；
 * aud(Audience)：代表这个JWT的接收对象；
 * exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
 * nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
 * iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
 * jti(JWT ID)：是JWT的唯一标识。
 */

//@Order(2)
//@WebFilter(urlPatterns = "/auth/*")
public class LoginTokenFilter extends GenericFilterBean {
    @Autowired
    private Audience audience;
    @Autowired
    private CommonFun commonFun;
    @Autowired
    private ResponseData responseData;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //只对post 请求做登录认证
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String authHeader = request.getHeader("authorization");
            if (StringUtils.isNullOrEmpty(authHeader) || !authHeader.startsWith("bearer;")) {
                commonFun.writer(response, responseData.autoError().sign(null));
                return;
            }
            final String token = authHeader.substring(7);
            if (audience == null) {
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                audience = (Audience) factory.getBean("audience");
            }
            final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
            if (claims == null) {
                commonFun.writer(response, responseData.autoError().sign(null));
                return;
            }
            try {
                long userId = Long.parseLong(claims.get("userId").toString());//从token里获取用户id
                if (userId <= 0) {
                    commonFun.writer(response, responseData.autoError().sign(null));
                    return;
                }
                if (!redisUtils.exists("JwtToken_" + userId)) {
                    commonFun.writer(response, responseData.autoError().sign(null));
                    return;
                }
                if (userService.userExist(userId) == null) {
                    commonFun.writer(response, responseData.autoError().sign(null));
                    return;
                }
                String data = request.getParameter("data");
                String machineId = request.getParameter("machineId");
                RequestData requestData = JsonUtils.GsonToBean(data, RequestData.class);
                //认证通过就修改掉请求参数
                MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);
                requestWrapper.setParameter("data", data);
                requestWrapper.setParameter("userId", userId);

            if (!StringUtils.isNullOrEmpty(machineId)) {
                    requestWrapper.setParameter("machineId", Long.parseLong(machineId));
                }
                filterChain.doFilter(requestWrapper, servletResponse);
                return;
            } catch (Exception ex) {
                commonFun.writer(response, responseData.autoError().sign(null));
                return;
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
