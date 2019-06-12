package com.wufanbao.api.oldclientservice.Tool;

/**
 * @program: alphaWuFan
 * @description: Token无效异常
 * @author: Wang Zhiyuan
 * @create: 2018-03-09 10:17
 **/
public class TokenException extends Exception {
    public String message;

    public TokenException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

