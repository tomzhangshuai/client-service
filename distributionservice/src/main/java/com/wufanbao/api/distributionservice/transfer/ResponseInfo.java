package com.wufanbao.api.distributionservice.transfer;

import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.CodeException;
import com.wufanbao.api.distributionservice.tools.Md5;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseInfo {

    /**
     * 数据字符串
     **/
    private String data;
    /**
     * 时间字符串
     */
    private String responseTime;
    /**
     * 签名字符串
     */
    private String digital;
    /**
     * 状态
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 返回结果结构体
     *
     * @param code        状态码
     * @param message     信息
     */
    public ResponseInfo(int code, String message) {
        this.code=code;
        this.message=message;
    }

    /**
     * 返回结果结构体
     * @param code        状态码
     */
    public ResponseInfo(Code code)
    {
        this.code=code.getCode();
        this.message=code.getMessage();
    }

    /**
     * 返回结果结构体
     * @param ex 状态错误yic
     */
    public ResponseInfo(CodeException ex)
    {
        this.setCode(ex.getCode());
        this.setMessage(ex.getMessage());
    }

    /**
     * 返回结果结构体
     * @param obj 数据
     */
    public ResponseInfo(Object obj)
    {
       this.code=Code.success.getCode();
       this.message="success";
       this.setTime();
       if(obj instanceof String)
       {
           this.data=(String)obj;
       }else {
           this.data=com.alibaba.fastjson.JSON.toJSONString(obj);
       }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public void setTime() {
        Date now = new Date();
        this.responseTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
    }

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * 校验数据是否被篡改
     *
     * @return
     */
    public boolean verification(String key) {
        String result = signResult(key);
        return result.equals(this.digital);
    }

    /**
     * 对结果签名
     *
     * @return
     */
    public String signResult(String key) {
        this.setTime();
        //responseInfo.getCode() + responseInfo.getMessage() + data + responseInfo.getResponseTime() + key
        String content = this.code + this.message + this.data + this.responseTime + key;
        String result = Md5.digital(content);
        this.digital=result;
        return result;

    }

}
