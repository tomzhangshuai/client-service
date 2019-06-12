package com.wufanbao.api.clientservice.common;

/**
 * 数据请求
 */
public class RequestData {
    //版本 必填
    private String version;
    //请求时间
    private String timestamp;
    //机器Id
    private String machineId;
    //请求的业务数据
    private String body;
    //请求数据的加密方式
    private String signType;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
