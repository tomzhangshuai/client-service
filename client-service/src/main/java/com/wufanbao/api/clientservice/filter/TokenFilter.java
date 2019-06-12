package com.wufanbao.api.clientservice.filter;


import com.alibaba.fastjson.JSON;
import com.wufanbao.api.clientservice.common.*;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;

@Order(1)
@Component
public class TokenFilter extends GenericFilterBean {

    @Autowired
    private SignUtils signUtils;

    @Autowired
    private CommonFun commonFun;

    @Autowired
    private ResponseData responseData;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        if (requestURI.contains("/webapi/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //只对post 请求做认证
        if ("POST".equalsIgnoreCase(request.getMethod()) ) {
            try {
                String data = request.getParameter("data");
                String sign = request.getParameter("sign");
                //签名验证
                RequestData requestData = JsonUtils.GsonToBean(data, RequestData.class);
                System.out.println("请求：" + requestData.getBody());
                 String mySign = signUtils.getSign(data, requestData.getTimestamp());
                if (!mySign.equals(sign)) {
                    commonFun.writer(response, responseData.signError().sign(null));
                    return;
                }
                //认证通过就修改掉请求
                MyHttpServletRequestWrapper requestWrapper = new MyHttpServletRequestWrapper(request);
                requestWrapper.setParameter("data", requestData.getBody() == null ? "" : requestData.getBody());
                String data2= requestWrapper.getParameter("data");
                if (!StringUtils.isNullOrEmpty(requestData.getMachineId())) {
                    requestWrapper.setParameter("machineId", requestData.getMachineId());
                }
                filterChain.doFilter(requestWrapper, servletResponse);
                return;
            } catch (Exception ex) {
                commonFun.writer(response, responseData.error(ex).sign(ex));
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}