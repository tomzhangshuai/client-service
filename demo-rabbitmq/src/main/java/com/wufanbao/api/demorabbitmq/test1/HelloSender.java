package com.wufanbao.api.demorabbitmq.test1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendA() {
        String context = "hello A" + new Date();
        amqpTemplate.convertAndSend("UserCouponEx62", "", context);
    }

    public void sendB() {
        String context = "hello B " + new Date();
        amqpTemplate.convertAndSend("UserCouponEx63", context);
    }
}
