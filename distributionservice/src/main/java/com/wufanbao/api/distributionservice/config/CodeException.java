package com.wufanbao.api.distributionservice.config;

/**
 * 带数字编码的错误
 */
public class CodeException extends Exception{

    /**
     * 错误码
     */
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 带数字编码的错误 构造函数
     * @param code
     * @param message
     */
    public CodeException(int code, String message){
        super(message);
        this.code=code;
    }

    /**
     * 带数字编码的错误 构造函数
     * @param code
     */
    public CodeException(Code code)
    {
        super(code.getMessage());
        this.code=code.getCode();
    }
}
