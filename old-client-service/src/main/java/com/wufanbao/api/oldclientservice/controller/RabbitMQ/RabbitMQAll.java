//package com.wufanbao.api.oldclientservice.controller.RabbitMQ;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//import java.util.ResourceBundle;
//import java.util.concurrent.TimeoutException;
//
///**
// * User:wangshihao
// * Date:2017-11-09
// * Time:16:49
// */
//@Configuration
//public class RabbitMQAll {
//
//    @Value("${spring.rabbitmq.host}")
//    private static String host;
//
//    @Value("${spring.rabbitmq.port}")
//    private static String port;
//
//    @Value("${spring.rabbitmq.username}")
//    private static String username;
//
//    @Value("${spring.rabbitmq.password}")
//    private static String password;
//
//    @Value("${com.wufanbao.orderLock}")
//    private static String OrderLock;
//
//    @Value("${com.wufanbao.orderUnlock}")
//    private static String OrderUnlock;
//
//    @Value("${com.wufanbao.takeFood}")
//    private static String TakeFood;
//
//
//    //锁单发送
//    public static void lockSend(Object message) throws IOException, TimeoutException {
//        ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection=factory.newConnection();
//        Channel channel=connection.createChannel();
//        channel.exchangeDeclare(OrderLock,"fanout");//fanout表示分发，所有的消费者得到同样的队列信息
//        //分发信息
//        channel.basicPublish(OrderLock,"",null,String.valueOf(message).getBytes());
//        System.out.println("锁单'" + message + "'");
//        channel.close();
//        connection.close();
//
//
//    }
//
//    public static void takeFood(Object message)throws IOException, TimeoutException{
//        /*ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection=factory.newConnection();
//        Channel channel=connection.createChannel();
//        //fanout表示分发，所有的消费者得到同样的队列信息
//        channel.exchangeDeclare(TakeFood,"fanout");
//        //分发信息
//        channel.basicPublish(TakeFood,"",null,String.valueOf(message).getBytes());
//        channel.close();
//        connection.close();*/
//    }
//    //解锁发送
//    public static void unLockSend(Object message) throws IOException, TimeoutException {
//        /*ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection=factory.newConnection();
//        Channel channel=connection.createChannel();
//        //fanout表示分发，所有的消费者得到同样的队列信息
//        channel.exchangeDeclare(OrderUnlock,"fanout");
//        //分发信息
//        channel.basicPublish(OrderUnlock,"",null,String.valueOf(message).getBytes());
//        System.out.println("解锁'" + message + "'");
//        channel.close();
//        connection.close();*/
//    }
//
//}
