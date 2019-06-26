package com.wufanbao.api.distributionservice.controllers;

import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.CodeException;
import com.wufanbao.api.distributionservice.config.Setting;
import com.wufanbao.api.distributionservice.transfer.ResponseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ControllerExceptionHandler {


    @Autowired
    Setting setting;

    /**
     *  拦截Exception类的异常
     * @param e 异常
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseInfo exceptionHandler(Exception e) {
        ResponseInfo result=null;
        if(e instanceof CodeException)
        {
            CodeException codeException=(CodeException)e;
            result=new ResponseInfo(codeException);
            result.signResult(setting.getSecurityKey());
            return result;
        }
        result = new ResponseInfo(Code.unknownError);
        result.signResult(setting.getSecurityKey());
        return result;
    }
}
