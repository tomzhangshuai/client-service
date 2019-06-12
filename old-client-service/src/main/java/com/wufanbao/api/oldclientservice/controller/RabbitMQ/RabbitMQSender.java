package com.wufanbao.api.oldclientservice.controller.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
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
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        channel.exchangeDeclare(OrderLock, "fanout");//fanout表示分发，所有的消费者得到同样的队列信息
//        //分发信息
//        channel.basicPublish(OrderLock, "", null, String.valueOf(message).getBytes());
//        System.out.println("锁单'" + message + "'");
//        channel.close();
//        connection.close();

        amqpTemplate.convertAndSend("OrderLock", "", String.valueOf(message).getBytes());
        System.out.println("锁单'" + message + "'");
    }

    public void takeFood(Object message) throws IOException, TimeoutException {
        /*ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setPort(5672);
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        //fanout表示分发，所有的消费者得到同样的队列信息
        channel.exchangeDeclare(TakeFood,"fanout");
        //分发信息
        channel.basicPublish(TakeFood,"",null,String.valueOf(message).getBytes());
        channel.close();
        connection.close();*/
        amqpTemplate.convertAndSend("MachineOrderFetch", "", String.valueOf(message).getBytes());
    }

    //解锁发送
    public void unLockSend(Object message) throws IOException, TimeoutException {
        /*ConnectionFactory factory=new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setPort(5672);
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        //fanout表示分发，所有的消费者得到同样的队列信息
        channel.exchangeDeclare(OrderUnlock,"fanout");
        //分发信息
        channel.basicPublish(OrderUnlock,"",null,String.valueOf(message).getBytes());
        System.out.println("解锁'" + message + "'");
        channel.close();
        connection.close();*/
        amqpTemplate.convertAndSend("OrderUnLock", "", String.valueOf(message).getBytes());
    }
}
