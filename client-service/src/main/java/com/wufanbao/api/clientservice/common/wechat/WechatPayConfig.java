package com.wufanbao.api.clientservice.common.wechat;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

//移动端配置
public class WechatPayConfig implements WXPayConfig {

    //APPID
    private String appid = "wx72b9fa2f86bba0cf";
    //密钥key
    private String key = "yF9W6rkSsXGU1ZefLc2kRNmWaExX5qIy";
    //商户号
    private static final String mchId = "1490321412";

    private static byte[] certData;

    static {
        try {
            File file = ResourceUtils.getFile("classpath:apiclient_cert.p12");
            InputStream certStream = new FileInputStream(file);
            certData = new byte[(int) file.length()];
            certStream.read(certData);
            certStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public WechatPayConfig() {
        appid = "wx72b9fa2f86bba0cf";
    }

    public WechatPayConfig(String appid) {
        this.appid = appid;
    }

//    private static WechatPayConfig Instance;
//    public static WechatPayConfig getInstance() throws Exception {
//        if (Instance == null) {
//            synchronized (WechatPayConfig.class) {
//                if (Instance == null) {
//                    Instance = new WechatPayConfig();
//                }
//            }
//        }
//        return Instance;
//    }


    @Override
    public String getAppID() {
        return appid;
    }

    @Override
    public String getMchID() {
        return mchId;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
