package com.wufanbao.api.distributionservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Setting {
    //版本
    @Value("${com.wufanbao.aesKey}")
    private String aesKey;
    //资源地址
    @Value("${com.wufanbao.resource}")
    private String resource;
    //AES 加密 key
    @Value("${com.wufanbao.securityKey}")
    private String securityKey;

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }
}
