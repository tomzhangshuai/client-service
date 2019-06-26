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

    /**
     * 数据内容
     */
    private String data;
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
        /*String content = "92cd57d4906e1cfeb2638510c8fa4a3b"+"{\"Mb\":\"13588224138\",\"passWord\":\"e10adc3949ba59abbe56e057f20f883e\"}"
                + "2019-06-18 10:45:09" + "02c646a93dfab08c8798815773cb1a29";
        String digital = Md5.digital(content).toLowerCase();
        String requet1="request={\"digital\":\"fc00d80a2629b6f9711f27b68c832009\",\"requestTime\":\"2019-06-18 10:45:09\",\"data\":{\"Mb\":\"13588224138\",\"passWord\":\"e10adc3949ba59abbe56e057f20f883e\"},\"requestId\":\"92cd57d4906e1cfeb2638510c8fa4a3b\",\"version\":\"1.0\"}";
        System.out.println(digital);

        String content2 = "92cd57d4906e1cfeb2638510c8fa4a3b{\"versionCode\":\"2.1.1\",\"versionType\":\"1\"}2019-06-18 10:45:0902c646a93dfab08c8798815773cb1a29";
        String digital2 = Md5.digital(content2).toLowerCase();
        String requet2="{\"digital\":\"0dfa922dd312a4bfc81432c0350427aa\",\"requestTime\":\"2019-06-18 10:45:09\",\"requestId\":\"92cd57d4906e1cfeb2638510c8fa4a3b\",\"version\":\"1.0\",\"data\":{\"versionCode\":\"2.1.1\",\"versionType\":\"1\"}}";
        System.out.println(digital2);*/

        String content3="92cd57d4906e1cfeb2638510c8fa4a3b{\"distributionOrderId\":2138919534593,\"remark\":\"没有什么可以阻挡我对自由的向往\"}2019-06-18 10:45:0902c646a93dfab08c8798815773cb1a29";
        String digital3 = Md5.digital(content3).toLowerCase();
        System.out.println(digital3);
        String requet3="{\"digital\":\"527682f295ab3e68d8b84b9658e272c9\"\"requestTime\":\"2019-06-18 10:45:09\",\"requestId\":\"92cd57d4906e1cfeb2638510c8fa4a3b\",\"version\":\"1.0\",\"data\":{\"distributionOrderId\":2138919534593,\"remark\":\"没有什么可以阻挡我对自由的向往\"}}";

    }
}
