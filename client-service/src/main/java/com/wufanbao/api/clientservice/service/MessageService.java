package com.wufanbao.api.clientservice.service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.wufanbao.api.clientservice.common.JsonUtils;
import com.wufanbao.api.clientservice.common.StringUtils;
import com.wufanbao.api.clientservice.common.wechat.*;
import com.xiaoleilu.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageService {
    @Autowired
    private WechatAuth wechatAuth;

    //消费端
    private  final String MASTER_SECRET = "0606d2fc182a9e498d6283ae";
    private final String APP_KEY = "1d846ee3dfdf68c01ce2fe51";

    //极光推送>>All所有平台
    @Transactional(rollbackFor = ApiServiceException.class)
    public  void jpushAll(Map<String, String> parm) throws  Exception{
        //创建JPushClient
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        //创建option
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())  //所有平台的用户
                .setAudience(Audience.alias(parm.get("id")))
//                .setAudience(Audience.all())
//                .setAudience(Audience.registrationId(parm.get("id")))//registrationId指定用户
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder() //发送ios
                                .setAlert(parm.get("msg")) //消息体
                                .setBadge(+1)
//                                .setSound("happy") //ios提示音
                                .addExtras(parm) //附加参数
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder() //发送android
                                .addExtras(parm) //附加参数
                                .setTitle(parm.get("title"))
                                .setAlert(parm.get("msg")) //消息体
                                .build())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(90)
                        .build())//指定开发环境 true为生产模式 false 为测试模式 (android不区分模式,ios区分模式)
                .setMessage(Message.newBuilder()
                        .setMsgContent(parm.get("id"))
                        .addExtras(parm)
                        .build())//自定义信息
                .build();
            PushResult pu = jpushClient.sendPush(payload);
            System.out.println(pu.toString());
    }

}
