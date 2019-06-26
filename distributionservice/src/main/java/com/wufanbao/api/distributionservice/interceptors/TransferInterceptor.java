package com.wufanbao.api.distributionservice.interceptors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.ProfileConfig;
import com.wufanbao.api.distributionservice.config.Setting;
import com.wufanbao.api.distributionservice.transfer.RequestInfo;
import com.wufanbao.api.distributionservice.transfer.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;


public class TransferInterceptor implements HandlerInterceptor {
    @Autowired
    Setting setting;
    @Autowired
    ProfileConfig profileConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String jsonString=request.getParameter("request");
        //开发环境 输出请求信息
        if(profileConfig.getActiveProfile().equals(ProfileConfig.DEV_PROFILE)) {
            StringBuilder sb = new StringBuilder();
            sb.append("请求地址：");
            sb.append(request.getRequestURI());
            sb.append("\r\n");
            sb.append("Request body:" + jsonString);
            System.out.println(sb.toString());
        }
        if (jsonString == null || jsonString.length()<1) {
            SetError(response, Code.parameterError.getCode(), "无法获取到数据");
            //无法获取到数据
            return false;
        }
//        JSONObject retObj = new JSONObject(true);
        RequestInfo p = JSON.parseObject(jsonString, new TypeReference<RequestInfo>() {},Feature.OrderedField);
        if (p == null) {
            SetError(response, Code.parameterError.getCode(), "无法序列化");
            //无法序列化
            return false;
        }
        boolean result = p.verification(setting.getSecurityKey());
        if (result) {
            //向request传递参数
            request.setAttribute("package", p);
        }else {
            SetError(response, Code.digitalError.getCode(), "数字签名验证错误");
        }
        return result;
    }

 /*   @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }*/

    private void SetError(HttpServletResponse response, int code, String message) {
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS");
        response.addHeader("Access-Control-Allow-Headers","authorization");
        response.addHeader("Access-Control-Max-Age","7200");
        response.setStatus(200);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"));
            ResponseInfo result = new ResponseInfo(code, message);
            result.signResult(setting.getSecurityKey());
            String resultString = JSON.toJSONString(result);
            writer.write(resultString);
        } catch (Exception ex) {
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Exception ex) {
                }
            }
        }
        // writer.close();

    }

}
