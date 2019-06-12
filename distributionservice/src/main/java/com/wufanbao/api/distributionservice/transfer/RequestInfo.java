package com.wufanbao.api.distributionservice.transfer;

import com.wufanbao.api.distributionservice.tools.CommonFun;
import com.wufanbao.api.distributionservice.tools.Md5;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 请求数据包
 */
public class RequestInfo {
    /**
     * 数据内容
     */
    private String data;

    /**
     * 签名摘要
     */
    private String digital;

    /**
     * 请求时间
     */
    private String requestTime;

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 版本
     */
    private String version;

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * 设置时间
     */
    private void setTime() {
        Date now = new Date();
        this.requestTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
    }

    /**
     *是否为空
     * @return
     */
    public boolean isEmpty()
    {
        return data==null||data.length()<1;
    }

    /**
     * 验证数据
     * @param key  摘要key
     * @return
     */
    public boolean verification(String key){

        //requestInfo.getRequestId() + requestInfo.getData() + requestInfo.getRequestTime() + key

        String md5Result=signResult(key);

        return md5Result.toLowerCase().equals(digital.toLowerCase());
    }

    /**
     * 对结果签名
     *
     * @return
     */
    private String signResult(String key) {

        String content=this.requestId+this.data+this.requestTime+key;
        return Md5.digital(content);

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        Date now=new Date();
        long time=now.getTime();
        System.out.println("time;"+time+"time2:"+System.currentTimeMillis());
        time=time-(time%(24*60*60*1000));
        System.out.println(time);
        Date day=new Date(time);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(now));
        System.out.println(format.format(day));
        String pp=Md5.digital("123456");
        RequestInfo info=new RequestInfo();
        //info.setData("{\"employeeId\":\"222\"}");

        /*
        info.setData("{\"mb\":\"13500007634\",\"password\":\"E10ADC3949BA59ABBE56E057F20F883E\"}");
        info.setTime();
        info.setRequestId("5EEC5DEE-637D-43C2-ACDC-D53E23515EDA");
        info.setVersion("1.0");
        info.setDigital(info.signResult("02c646a93dfab08c8798815773cb1a29"));
        */
       /// info.setData("{\n  \"mb\" : \"13500007634\",\n  \"password\" : \"e10adc3949ba59abbe56e057f20f883e\"\n}");

        String ss=com.alibaba.fastjson.JSON.toJSONString(info);
        System.out.println(ss);
        pp= CommonFun.encryptionPassword(pp);
        System.out.println(pp);
    }

}
