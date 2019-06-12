package com.wufanbao.api.olddriverservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class DriverSetting {
    //版本
    @Value("${com.wufanbao.version}")
    private String version;
    //资源地址
    @Value("${com.wufanbao.resource}")
    private String resource;
    //AES 加密 key
    @Value("${com.wufanbao.aeskey}")
    private String aeskey;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getAeskey() {
        return aeskey;
    }

    public void setAeskey(String aeskey) {
        this.aeskey = aeskey;
    }
}
