package com.wufanbao.api.demorabbitmq.test2;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendA() {
        String context = "Test A" + new Date();
        amqpTemplate.convertAndSend("UserCouponEx61", "", context);
    }

}
