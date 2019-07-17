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
import com.wufanbao.api.clientservice.common.IDGenerator;
import com.wufanbao.api.clientservice.common.JsonUtils;
import com.wufanbao.api.clientservice.common.StringUtils;
import com.wufanbao.api.clientservice.common.wechat.*;
import com.wufanbao.api.clientservice.dao.MessageDao;
import com.wufanbao.api.clientservice.dao.UserDao;
import com.wufanbao.api.clientservice.entity.MessageInfo;
import com.wufanbao.api.clientservice.entity.MessageInfoContentType;
import com.wufanbao.api.clientservice.entity.MessageInfoType;
import com.wufanbao.api.clientservice.entity.UserMessage;
import com.xiaoleilu.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class MessageService {
    @Autowired
    private WechatAuth wechatAuth;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;

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
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder() //发送ios
                                .setAlert(parm.get("msg")) //消息体
                                .setBadge(+1)
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

    @Transactional(rollbackFor = ApiServiceException.class)
    public void insertMessage(String userId, String message, String content) throws ApiServiceException {
        if(StringUtils.isNullOrEmpty(userId)||StringUtils.isNullOrEmpty(message)){
            throw new ApiServiceException("数据错误");
        }
        long messageinfoId = IDGenerator.generate("Messageinfo");
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setMessageInfoId(messageinfoId);
        messageInfo.setContentType(1);
        messageInfo.setContent(message+" "+content);
        messageInfo.setMessageType(2);
        messageInfo.setIsActive(true);
        if(messageDao.insertMessageInfo(messageInfo)<0){
            throw new ApiServiceException("插入消息信息详情失败");
        }
        UserMessage userMessage=new UserMessage();
        userMessage.setMessageInfoId(messageinfoId);
        userMessage.setUserId(Long.parseLong(userId));
        userMessage.setRead(false);
        userMessage.setDeleted(false);
        if(messageDao.insertUserMessage(userMessage)<0){
            throw new ApiServiceException("插入用户消息失败");
        }
    }

}
