package com.wufanbao.api.demorabbitmq.test2;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class TestReceiver {

    //@RabbitListener(bindings ={@QueueBinding(value = , exchange = @Exchange(value = "UserCouponEx61")) })
    //@RabbitListener(bindings = @QueueBinding(value = @Queue( value = "#{anonymousQueue.name}",durable = "true",autoDelete = "false"),exchange = @Exchange(value = "UserCouponEx61",type = "fanout")))
    @RabbitListener(queues = "#{anonymousQueue.name}")
    public void process(String hello) {
        System.out.println("Receiver-all:" + hello);
    }
}
