package com.wufanbao.api.distributionservice.resolvers;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.CodeException;
import com.wufanbao.api.distributionservice.transfer.RequestInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class CustomResolver implements HandlerMethodArgumentResolver {

    public boolean supportsParameter(MethodParameter parameter) {
        //return true;
        return parameter.hasParameterAnnotation(Custom.class);
    }


    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        RequestInfo p = (RequestInfo) webRequest.getAttribute("package", 0);
        if (p == null) {
            return null;
        }

        if (p.isEmpty()) {
            return null;
        }

        try {
            return JSON.parseObject(p.getData(), parameter.getParameterType());
        }catch (Exception ex) {
            throw new CodeException(Code.parameterError);
        }
    }


}
