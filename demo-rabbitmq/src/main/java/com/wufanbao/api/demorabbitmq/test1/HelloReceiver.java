package com.wufanbao.api.demorabbitmq.test1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.stereotype.Component;

@Component

public class HelloReceiver {

    @RabbitListener(queues = "#{AMessage.name}")
    public void process(String hello) {
        System.out.println("Receiver A:" + hello);
    }
    @RabbitListener(queues = "#{BMessage.name}")
    public void process1(String hello) {
        System.out.println("Receiver B:" + hello);
    }

}
