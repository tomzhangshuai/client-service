package com.wufanbao.api.olddriverservice.controller.RabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

/**
 * User:Wangshihao
 * Date:2017-10-10
 * Time:9:57
 */
@Component
public class QueueSend {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //锁单发送
    public void deliveryOver(Object message) throws IOException, TimeoutException {
//        ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection=factory.newConnection();
//        Channel channel=connection.createChannel();
//        //fanout表示分发，所有的消费者得到同样的队列信息
//        channel.exchangeDeclare(supplementCompleted,"fanout");
//        //分发信息
//        channel.basicPublish(supplementCompleted,"",null,String.valueOf(message).getBytes());
//        System.out.println("配送完成" + message + "'");
//        channel.close();
//        connection.close();
        amqpTemplate.convertAndSend("SupplementCompleted", "", String.valueOf(message).getBytes());
    }

    /**
     * 开仓
     *
     * @param message
     * @return void
     * @date 2018/7/3
     */
    public void machineSupplementFront(Object message) throws IOException, TimeoutException {
//        ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection=factory.newConnection();
//        Channel channel=connection.createChannel();
//        //fanout表示分发，所有的消费者得到同样的队列信息
//        channel.exchangeDeclare(machineSupplementFront,"fanout");
//        //分发信息
//        channel.basicPublish(machineSupplementFront,"",null,String.valueOf(message).getBytes());
//        channel.close();
//        connection.close();

        amqpTemplate.convertAndSend("MachineSupplementFront", "", String.valueOf(message).getBytes());
    }

    public void machineSupplementBehind(Object message) throws IOException, TimeoutException {
//        ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        Connection connection=factory.newConnection();
//        Channel channel=connection.createChannel();
//        //fanout表示分发，所有的消费者得到同样的队列信息
//        channel.exchangeDeclare(machineSupplementBehind,"fanout");
//        //分发信息
//        channel.basicPublish(machineSupplementBehind,"",null,String.valueOf(message).getBytes());
//        channel.close();
//        connection.close();

        amqpTemplate.convertAndSend("MachineSupplementBehind", "", String.valueOf(message).getBytes());
    }

//后仓
//    public static void backStorehouse(Object message) throws IOException, TimeoutException {
//        //创建连接连接到rabbitMQ
//        ConnectionFactory factory=new ConnectionFactory();
//        factory.setHost(host);
//        factory.setUsername(username);
//        factory.setPassword(password);
//        factory.setPort(5672);
//        //创建一个连接
//        Connection connection=factory.newConnection();
//        //常见一个频道
//        Channel channel=connection.createChannel();
//        // 指定一个队列         fanout表示分发，所有的消费者得到同样的队列信息
//        channel.exchangeDeclare(supplementCompleted,"fanout");
//        //往队列中发一条消息
//        channel.basicPublish(supplementCompleted,"",null,String.valueOf(message).getBytes());
//        System.out.println("配送完成" + message + "'");
//        channel.close();
//        connection.close();
//    }


}
