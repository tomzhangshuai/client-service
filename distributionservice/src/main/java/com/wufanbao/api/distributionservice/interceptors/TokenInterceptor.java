package com.wufanbao.api.distributionservice.interceptors;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.ProfileConfig;
import com.wufanbao.api.distributionservice.config.Setting;
import com.wufanbao.api.distributionservice.tools.Md5;
import com.wufanbao.api.distributionservice.tools.RedisUtils;
import com.wufanbao.api.distributionservice.tools.Token;
import com.wufanbao.api.distributionservice.tools.TokenFactor;
import com.wufanbao.api.distributionservice.transfer.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    Setting setting;
    @Autowired
    ProfileConfig profileConfig;
    @Autowired
    RedisUtils redisUtils;
    /**
     * token存储前缀
     */
    private final String TokenStorePrefix="DSAPI_Token_";
    /**
     * 员工存储前缀
     */
    private final String EmployeeStorePrefix="DSAPI_Employee_";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
      /* //现由 web config 配置
        String requestURI = request.getRequestURI().toLowerCase();
        if (requestURI.contains("/employee/login")||requestURI.contains("/employeelogin/employeelogininfo")) {
            return true;//登录页面不验证token
        }*/
        String token=request.getHeader("Authorization");
        if(token==null||token.length()<1) {
            SetError(response,Code.voucherDeficiency.getCode(),"用户凭证丢失");
            return false;
        }
        TokenFactor tokenFactor=Token.GetFactor(setting.getAesKey(),token);
        if(!tokenFactor.isEffectived()){
            SetError(response,Code.voucherError.getCode(),"用户会话错误或不合法-1");
            return  false;
        }
        String userAgent=request.getHeader("User-Agent");
        String md5UserAgent= Md5.digital(userAgent);
        if(!md5UserAgent.equals(tokenFactor.getUserAgentMd5())) {
            SetError(response,Code.voucherError.getCode(),"用户会话错误或不合法-2");
            return  false;
        }
        String employeeInfoString=redisUtils.get(TokenStorePrefix+token);
        if(employeeInfoString!=null){
            request.setAttribute("employeeInfo",employeeInfoString);
        }else {
            String employeeId=tokenFactor.getEmployeeId();
            if(redisUtils.exists(EmployeeStorePrefix+employeeId)) {
                SetError(response, Code.sessionInvaild.getCode(), "用户在其他设备登录");
            }else {
                SetError(response, Code.sessionInvaild.getCode(), "用户会话过期");
            }
            return  false;
        }
        return true;
    }

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
