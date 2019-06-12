package com.wufanbao.api.oldclientservice.service;

/**
 * User:Wangshihao
 * Date:2017-10-10
 * Time:14:32
 */
public interface MQProducer {
    public void sendDataToQueue(String queueKey, Object object);
}
