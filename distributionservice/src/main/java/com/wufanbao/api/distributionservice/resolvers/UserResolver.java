package com.wufanbao.api.distributionservice.resolvers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wufanbao.api.distributionservice.transfer.EmployeeInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserResolver implements HandlerMethodArgumentResolver {
    public boolean supportsParameter(MethodParameter parameter) {
        //return true;
        return parameter.hasParameterAnnotation(User.class);
    }


    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {


        String employeeInfoString = (String) webRequest.getAttribute("employeeInfo", 0);
        if (employeeInfoString == null || employeeInfoString.length() < 1) {
            return new EmployeeInfo();
        }
        return JSON.parseObject(employeeInfoString, new TypeReference<EmployeeInfo>() {
        });

    }
}
