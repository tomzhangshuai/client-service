package com.wufanbao.api.clientservice.common.wechat;

import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.service.ApiServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//微信授权
@Component
public class WechatAuth {
    @Autowired
    private CommonFun commonFun;
    @Autowired
    private RedisUtils redisUtils;

    //正式号
    private static final String appId = "wxebf93a9cdec42081";
    private final static String appSecret = "91c97211b574efd1efdb21b96013f7a4";


    //通过code获取access_token
    public AccessToken getAccessTokenByCode(String code) throws ApiServiceException {
        if (StringUtils.isNullOrEmpty(code)) {
            return null;
        }
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        String result = commonFun.sendGet(url);
        AccessToken accessToken = JsonUtils.GsonToBean(result, AccessToken.class);
        if (!StringUtils.isNullOrEmpty(accessToken.getErrmsg())) {
            throw new ApiServiceException(accessToken.getErrmsg());
        }
        storeAccessToken(accessToken);
        return accessToken;
    }

    //获取基础支持的access_token
    public String getBasicAccessToken() throws ApiServiceException {
       /* if(redisUtils.exists("basicAccessToken")){
           return  redisUtils.get("basicAccessToken");
        }*/
        String resultUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId +"&secret="+ appSecret;
        String result = commonFun.sendGet(resultUrl);
        BasicAccessToken basicAccessToken = JsonUtils.GsonToBean(result, BasicAccessToken.class);
        if (!StringUtils.isNullOrEmpty(basicAccessToken.getErrmsg())) {
            throw new ApiServiceException(basicAccessToken.getErrmsg());
        }
      /*  redisUtils.set("basicAccessToken", basicAccessToken.getAccess_token());
        redisUtils.expire("basicAccessToken", 604800);//7*/
//        storeAccessToken(accessToken);
        return basicAccessToken.getAccess_token();
    }

    //刷新
    public AccessToken refreshToken(String openId) throws ApiServiceException {
        String key = "Refresh_Token_" + openId;
        String refreshToken = redisUtils.get(key);
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + appId + "&grant_type=refresh_token&refresh_token=" + refreshToken;
        String result = commonFun.sendGet(url);
        AccessToken accessToken = JsonUtils.GsonToBean(result, AccessToken.class);
        if (!StringUtils.isNullOrEmpty(accessToken.getErrmsg())) {
            throw new ApiServiceException(accessToken.getErrmsg());
        }
        storeAccessToken(accessToken);
        return accessToken;
    }

    //存储
    private void storeAccessToken(AccessToken accessToken) {
        String accessKey = "Access_Token_" + accessToken.getOpenid();
        redisUtils.set(accessKey, accessToken.getAccess_token());
        redisUtils.expire(accessKey, accessToken.getExpires_in());
        String refreshKey = "Refresh_Token_" + accessToken.getOpenid();
        redisUtils.set(refreshKey, accessToken.getRefresh_token());
        redisUtils.expire(refreshKey, 2592000);//30
    }


    private String getAccessToken() throws ApiServiceException {
        String key = "Access_Token_Sign";
        if (redisUtils.exists(key)) {
            return redisUtils.get(key);
        }
        return getAccessTokenFromWx();
    }

    private String getAccessTokenFromWx() throws ApiServiceException {
        String key = "Access_Token_Sign";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        String result = commonFun.sendGet(url);
        Data data = JsonUtils.GsonToBean(result, Data.class);
        if (data.get("errcode") != null && data.get("access_token") == null) {
            throw new ApiServiceException(data.get("errmsg").toString());
        }
        String access_token = data.get("access_token").toString();
        String expires_in = data.get("expires_in").toString();
        redisUtils.set(key, access_token);
        redisUtils.expire(key, 7200);
        return access_token;
    }

    private String getJsapiTicket() throws ApiServiceException {
        String key = "jsapi_ticket";
        if (redisUtils.exists(key)) {
            return redisUtils.get(key);
        }
        return getJsapiTicketFromWx();
    }

    private String getJsapiTicketFromWx() throws ApiServiceException {
        String key = "jsapi_ticket";
        String accessToken = getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken + "&type=jsapi";
        String result = commonFun.sendGet(url);
        Data data = JsonUtils.GsonToBean(result, Data.class);
        if (data.get("errcode") != null && data.get("ticket") == null) {//可能会失效
            getAccessTokenFromWx();
            getJsapiTicketFromWx();
        }
        String ticket = data.get("ticket").toString();
        redisUtils.set(key, ticket);
        redisUtils.expire(key, 7200);
        return ticket;
    }

    //获取用户信息
    public UserInfo getUserInfo(String openId) throws ApiServiceException {
        AccessToken accessToken = refreshToken(openId);
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken.getAccess_token() + "&openid=" + openId;
        String result = commonFun.sendGet(url);
        UserInfo userInfo = JsonUtils.GsonToBean(result, UserInfo.class);
        return userInfo;
    }

    //获取签名
    public Data getConfig(String url) throws ApiServiceException {
        Sign sign = new Sign();
        String jsapi_ticket = getJsapiTicket();
        String nonce_str = sign.create_nonce_str();
        String timestamp = sign.create_timestamp();
        String str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        Data data = new Data();
        data.put("url", url);
        data.put("jsapi_ticket", jsapi_ticket);
        data.put("nonceStr", nonce_str);
        data.put("timestamp", timestamp);
        data.put("signature", sign.sign(str, "SHA-1"));
        return data;
    }

    public Data getPay(String tradeNo, String openId, BigDecimal amount, String body, String ip, String callback) throws Exception {
        Sign sign = new Sign();
        String nonce_str = sign.create_nonce_str();
        String timestamp = sign.create_timestamp();

        WechatPay wechatPay = new WechatPay(appId);
        String str = wechatPay.getConfig().getAppID();
        Map<String, String> map = wechatPay.payOrder(tradeNo, "JSAPI", openId, amount, body, ip, callback);

        String packageStr = "prepay_id=" + map.get("prepayid");

        Map<String, String> newMap = new HashMap<>();
        newMap.put("appId", appId);
        newMap.put("nonceStr", nonce_str);
        newMap.put("timeStamp", timestamp);
        newMap.put("package", packageStr);
        newMap.put("signType", "MD5");
        String signStr = WXPayUtil.generateSignature(newMap, wechatPay.getConfig().getKey(), WXPayConstants.SignType.MD5);

        Data data = new Data();
        data.put("nonceStr", nonce_str);
        data.put("timestamp", timestamp);
        data.put("package", packageStr);
        data.put("signType", "MD5");
        data.put("paySign", signStr);
        return data;
    }

    public class Sign {
        public String sign(String str, String signType) {
            String signature = "";
            try {
                MessageDigest crypt = MessageDigest.getInstance(signType);
                crypt.reset();
                crypt.update(str.getBytes("UTF-8"));
                signature = byteToHex(crypt.digest());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return signature;
        }

        private String byteToHex(final byte[] hash) {
            Formatter formatter = new Formatter();
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            String result = formatter.toString();
            formatter.close();
            return result;
        }

        public String create_nonce_str() {
            return UUID.randomUUID().toString();
        }

        public String create_timestamp() {
            return Long.toString(System.currentTimeMillis() / 1000);
        }

    }
}
