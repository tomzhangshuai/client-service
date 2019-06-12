/*
//package com.wufanbao.api.clientservice.common.wechat;
//
//import com.wufanbao.api.clientservice.common.CommonFun;
//import com.wufanbao.api.clientservice.common.JsonUtils;
//import com.wufanbao.api.clientservice.common.RedisUtils;
//import com.wufanbao.api.clientservice.common.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WechatAppAuth {
//    @Autowired
//    private CommonFun commonFun;
//    @Autowired
//    private RedisUtils redisUtils;
//
//    private static final String appId = "wx72b9fa2f86bba0cf";
//    private final static String appSecret = "168fcc0c065a3facf1566cef48a38d7e";
//
//    private static final String notify_url = "http://wfb.yusubao.com:8003";
//
//
//    //通过code获取access_token
//    public AccessToken getAccessTokenByCode(String code) {
//        if (StringUtils.isNullOrEmpty(code)) {
//            return null;
//        }
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
//        String result = commonFun.sendGet(url);
//        AccessToken accessToken = JsonUtils.GsonToBean(result, AccessToken.class);
//        storeAccessToken(accessToken);
//        return accessToken;
//    }
//
//    //刷新
//    public AccessToken refreshToken(String openId) {
//        String key = "App_Refresh_Token_" + openId;
//        String refreshToken = redisUtils.get(key);
//        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + appId + "&grant_type=refresh_token&refresh_token=" + refreshToken;
//        String result = commonFun.sendGet(url);
//
//        AccessToken accessToken = JsonUtils.GsonToBean(result, AccessToken.class);
//
//        storeAccessToken(accessToken);
//        return accessToken;
//    }
//
//    //存储
//    private void storeAccessToken(AccessToken accessToken) {
//        String accessKey = "App_Access_Token_" + accessToken.getOpenid();
//        redisUtils.set(accessKey, accessToken.getAccess_token());
//        redisUtils.expire(accessKey, accessToken.getExpires_in());
//
//        String refreshKey = "App_Refresh_Token_" + accessToken.getOpenid();
//        redisUtils.set(refreshKey, accessToken.getRefresh_token());
//        redisUtils.expire(refreshKey, 2592000);//30
//    }
//
//
//    //获取用户信息
//    public UserInfo getUserInfo(String openId) {
//        AccessToken accessToken = refreshToken(openId);
//        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken.getAccess_token() + "&openid=" + openId;
//        String result = commonFun.sendGet(url);
//        UserInfo userInfo = JsonUtils.GsonToBean(result, UserInfo.class);
//        return userInfo;
//    }
//}
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.google.gson.Gson;
import com.phjr.service.huifu.util.HttpClientUtil;


import weixn.Demo;

import java.io.IOException;


public class TuiSongTest {


    private static Logger logger = LoggerFactory.getLogger(TuiSongTest.class);


    //微信模板接口
    private final String SEND_TEMPLAYE_MESSAGE_URL ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    private final String APPID = "";
    private final String SECRET = "";
    //获取微信ACCESS_TOKEN接口
    private final String aturl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID
            + "&secret=" + SECRET;
    //openId
    private static  String fromUserName="";
    //模板ID
    private static String template_id="";
    //模板消息详情跳转URL
    private static String url="";


    public static void main(String[] args) {
        TuiSongTest tuiSongTest = new TuiSongTest();

        Demo demo = tuiSongTest.getAccess_token();
        String access_token=demo.getAccess_token();

        logger.info("为模板消息接口获取的accessToken是"+access_token);  

        WechatTemplate wechatTemplate = new WechatTemplate();  
        wechatTemplate.setTemplate_id(template_id);  
        wechatTemplate.setTouser(fromUserName);  //此处是用户的OpenId
        wechatTemplate.setUrl(url); 


private static Logger logger = LoggerFactory.getLogger(TuiSongTest.class);


//微信模板接口
private final String SEND_TEMPLAYE_MESSAGE_URL ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

private final String APPID = "";
private final String SECRET = "";
//获取微信ACCESS_TOKEN接口
private final String aturl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
//openId
private static String fromUserName="";
//模板ID
private static String template_id="";
//模板消息详情跳转URL
private static String url="";


public static void main(String[] args) {
        TuiSongTest tuiSongTest = new TuiSongTest();

        Demo demo = tuiSongTest.getAccess_token();
        String access_token=demo.getAccess_token();

        logger.info("为模板消息接口获取的accessToken是"+access_token);  

        WechatTemplate wechatTemplate = new WechatTemplate();  
        wechatTemplate.setTemplate_id(template_id);  
        wechatTemplate.setTouser(fromUserName);  //此处是用户的OpenId
        wechatTemplate.setUrl(url); 


 Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
                TemplateData first = new TemplateData();   
                first.setColor("#000000"); 
                first.setValue("您的一位好友完成了注册");  
                m.put("first", first);
                TemplateData keyword1 = new TemplateData();
                keyword1.setColor("#000000");
                keyword1.setValue("136****1234");
                m.put("keyword1", keyword1);  
                TemplateData keyword2 = new TemplateData();
                keyword2.setColor("#000000");
                keyword2.setValue("2018-06-10 10:23:00");
                m.put("keyword2", keyword2);
                TemplateData remark = new TemplateData();
                remark.setColor("#000000");
                remark.setValue("用户136****1234是您的直接推荐好友");
                m.put("remark", remark);
                wechatTemplate.setData(m);
                try {   
                    tuiSongTest.sendTemplateMessage(access_token, wechatTemplate);  
                    } catch (Exception e) {       
                        logger.info("异常"+e.getMessage());  
                }  
                }

            public void sendTemplateMessage(String accessToken, WechatTemplate wechatTemplate) {           
        String jsonString = new Gson().toJson(wechatTemplate).toString();          
        String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);        
        logger.info("请求参数",jsonString);     //发送 post请求 发送json数据     
        String json = HttpClientUtil.sendHttpPostJson(requestUrl, jsonString);           
        WeiXinResponse weiXinResponse = new Gson().fromJson(json, WeiXinResponse.class);     
        logger.info("jsonObject="+weiXinResponse);     if (null != weiXinResponse) {           
        int errorCode = weiXinResponse.getErrcode();            
        if (0 == errorCode) {              
         logger.info("模板消息发送成功");           
        } else {               
        String errorMsg = weiXinResponse.getErrmsg();             
        logger.info("模板消息发送失败,错误是 "+errorCode+",错误信息是"+ errorMsg);           
        }       
        }   
}   
public Demo getAccess_token(){
    try {
        String access_token = "";
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(aturl);
        HttpResponse response = client.execute(request);
        String httpGet = HttpClientUtil.sendHttpGet(aturl);
        Gson gson=new Gson();
        Demo jsonResult=new Demo();
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String strResult = EntityUtils.toString(response.getEntity());      
        System.out.println("get请求结果:" + strResult);         
        System.out.println("结果回调" +response.getStatusLine().getStatusCode() );          
        jsonResult = gson.fromJson(strResult, Demo.class); access_token = jsonResult.getAccess_token();
        String expires_in =jsonResult.getExpires_in();
        logger.info("access_token{}:  expires_in{}:",access_token,expires_in);
        }
        return jsonResult;
    } catch (ClientProtocolException e){
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
    }
    }
}
*/





















