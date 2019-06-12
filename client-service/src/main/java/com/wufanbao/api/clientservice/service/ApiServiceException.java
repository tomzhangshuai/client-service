package com.wufanbao.api.clientservice.service;

public class ApiServiceException extends Exception {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ApiServiceException(String message) {
        super(message);
    }

    public ApiServiceException(int code, String message) {
        super(message);
        setCode(code);
    }
}
