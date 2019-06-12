package com.wufanbao.api.oldclientservice.Tool;

public class BaseController {
    protected String retContent(int code, Object data) {
        return ReturnFormat.retParam(code, data);
    }
}
