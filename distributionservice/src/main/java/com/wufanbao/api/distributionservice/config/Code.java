package com.wufanbao.api.distributionservice.config;

public enum Code {
    success("成功", 1),
    digitalError("数字签名验证错误", 100),
    parameterError("参数格式不正确", 101),


    voucherDeficiency("缺失凭证",111),
    voucherError("凭证错误",112),
    sessionInvaild("凭证已失效",113),

    dataError("数据不正确",121),
    dataInvaild("数据已过期",122),


    employeeLoginError("用户名或密码错误", 2001),
    employeeForbid("您的账号已被禁用", 2002),
    employeeNotAllow("您的账号未授权登录本系统", 2003),

    scanInvaild("未扫码或扫码过期失效",2011),
    unknownError("未知错误",5000);

    Code(String message, int code) {
        this.message = message;
        this.code = code;
    }

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
