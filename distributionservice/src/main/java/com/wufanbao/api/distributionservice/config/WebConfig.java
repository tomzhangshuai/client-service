package com.wufanbao.api.distributionservice.config;


import com.wufanbao.api.distributionservice.interceptors.TokenInterceptor;
import com.wufanbao.api.distributionservice.interceptors.TransferInterceptor;
import com.wufanbao.api.distributionservice.resolvers.CustomResolver;
import com.wufanbao.api.distributionservice.resolvers.UserResolver;
import com.wufanbao.api.distributionservice.returnValueHandles.MyReturnHandler;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer,WebMvcRegistrations {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getTestInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/index");
        registry.addInterceptor(getTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/index","/employee/login","/employeelogin/employeelogininfo","/employeeLogin/employeeLoginInfo");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getCustomResolver());
        resolvers.add(getUserResolver());
    }

    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter() {
            @Override
            public void afterPropertiesSet() {
                // 由父类方法加载所有的 HandlerMethodReturnValueHandler
                super.afterPropertiesSet();
                // 不可修改的List
                List<HandlerMethodReturnValueHandler> returnValueHandlers = super.getReturnValueHandlers();
                // returnValueHandlers副本
                List<HandlerMethodReturnValueHandler> newreturnValueHandlers = new ArrayList<>(returnValueHandlers);
                // 将自定义的Handler添加的首位
                newreturnValueHandlers.set(0,getMyReturnHandler());
                // 设置新值
                super.setReturnValueHandlers(newreturnValueHandlers);
            }
        };
    }

    @Bean //将自定义拦截器注册到spring bean中
    public TransferInterceptor getTestInterceptor() {
        return new TransferInterceptor();
    }

    @Bean
    public TokenInterceptor getTokenInterceptor() {
        return  new TokenInterceptor();
    }

    @Bean
    public CustomResolver getCustomResolver() {
        return new CustomResolver();
    }

    @Bean
    public UserResolver getUserResolver() {
        return new UserResolver();
    }

    @Bean
    public MyReturnHandler getMyReturnHandler(){
        return  new MyReturnHandler();
    }
}
