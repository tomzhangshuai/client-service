package com.wufanbao.api.clientservice.common.rabbitMQ;

import com.wufanbao.api.clientservice.entity.UserOrder;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    //锁单发送
    public void lockSend(Object message) throws IOException, TimeoutException {
        amqpTemplate.convertAndSend("OrderLock", "", String.valueOf(message).getBytes());
        System.out.println("锁单'" + message + "'");
    }
    public void takeFood(Object message) throws IOException, TimeoutException {
        amqpTemplate.convertAndSend("MachineOrderFetch", "", String.valueOf(message).getBytes());
    }
    public void sendTakeFoodPush(Object message) throws IOException, TimeoutException {
        amqpTemplate.convertAndSend("SendMachineOrderFetch", "", String.valueOf(message).getBytes());
    }
    //解锁发送
    public void unLockSend(Object message) throws IOException, TimeoutException {
        amqpTemplate.convertAndSend("OrderUnLock", "", String.valueOf(message).getBytes());
    }
    public void userpaybindSend(Object message){
        amqpTemplate.convertAndSend("userpayBind","",String.valueOf(message).getBytes());
    }
    public void userpayUnbindSend(Object message){
        amqpTemplate.convertAndSend("userpayUnBind","",String.valueOf(message).getBytes());
    }
}
