package com.wufanbao.api.distributionservice.returnValueHandles;


import com.wufanbao.api.distributionservice.config.ProfileConfig;
import com.wufanbao.api.distributionservice.config.Setting;
import com.wufanbao.api.distributionservice.transfer.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

public class MyReturnHandler implements HandlerMethodReturnValueHandler {

    @Autowired
   Setting setting;

    @Autowired
    ProfileConfig profileConfig;

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        //return methodParameter.hasMethodAnnotation(ResponseBody.class);
        boolean isIgnore=methodParameter.hasMethodAnnotation(IgnoreReturnHandler.class);
        if(isIgnore){
            return false;
        }
        return true;
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest)
            throws Exception {
        modelAndViewContainer.setRequestHandled(true);
        String objectData="";
        if(o!=null){
            if(o.getClass().equals(String.class))
            {
                objectData= (String)o;
            }else {
                objectData = com.alibaba.fastjson.JSON.toJSONString(o);
            }
        }

        ResponseInfo responseInfo =new ResponseInfo(objectData);

        responseInfo.signResult(setting.getSecurityKey());

        String result=com.alibaba.fastjson.JSON.toJSONString(responseInfo);

        //开发环境 输出请求信息
        if(profileConfig.getActiveProfile().equals(ProfileConfig.DEV_PROFILE)) {
            StringBuilder sb = new StringBuilder();
            sb.append("返回结果:");
            sb.append(((ServletWebRequest)nativeWebRequest).getRequest().getRequestURL());
            sb.append("\r\n");
            sb.append(result);
            System.out.println(sb.toString());
        }

        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        response.addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(result);
    }
}
