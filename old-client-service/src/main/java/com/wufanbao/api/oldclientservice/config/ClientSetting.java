package com.wufanbao.api.oldclientservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class ClientSetting {
    @Value("${com.wufanbao.version}")
    private String version;
    //资源地址
    @Value("${com.wufanbao.resource}")
    private String resource;
    //文件上传地址
    @Value("${com.wufanbao.uploadUrl}")
    private String uploadUrl;

    @Value("${com.wufanbao.AES_256key}")
    private String AES_256key;

    @Value("${com.wufanbao.alipaycallback}")
    private String alipaycallback;

    @Value("${com.wufanbao.aliPayH5Url}")
    private String aliPayH5Url;

    @Value("${com.wufanbao.shareLinkUrl}")
    private String shareLinkUrl;

    @Value("${com.wufanbao.shareLinkImages}")
    private String shareLinkImages;

    @Value("${com.wufanbao.tokenKey}")
    private String tokenKey;

    @Value("${com.wufanbao.mUrl}")
    private String mUrl;

    @Value("${com.wufanbao.wechatCallback}")
    private String wechatCallback;


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

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getAES256Key() {
        return AES_256key;
    }

    public void setAES256Key(String AES256Key) {
        this.AES_256key = AES256Key;
    }

    public String getAlipaycallback() {
        return alipaycallback;
    }

    public void setAlipaycallback(String alipaycallback) {
        this.alipaycallback = alipaycallback;
    }

    public String getAliPayH5Url() {
        return aliPayH5Url;
    }

    public void setAliPayH5Url(String aliPayH5Url) {
        this.aliPayH5Url = aliPayH5Url;
    }

    public String getShareLinkUrl() {
        return shareLinkUrl;
    }

    public void setShareLinkUrl(String shareLinkUrl) {
        this.shareLinkUrl = shareLinkUrl;
    }

    public String getShareLinkImages() {
        return shareLinkImages;
    }

    public void setShareLinkImages(String shareLinkImages) {
        this.shareLinkImages = shareLinkImages;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getWechatCallback() {
        return wechatCallback;
    }

    public void setWechatCallback(String wechatCallback) {
        this.wechatCallback = wechatCallback;
    }
}
