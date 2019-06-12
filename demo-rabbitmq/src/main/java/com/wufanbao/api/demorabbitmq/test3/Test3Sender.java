package com.wufanbao.api.demorabbitmq.test3;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Test3Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendA() {
        String context = "Test A" + new Date();
        amqpTemplate.convertAndSend("UserCouponEx62", "", context);
    }

}
