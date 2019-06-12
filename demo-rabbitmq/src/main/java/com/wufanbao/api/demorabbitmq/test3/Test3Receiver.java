package com.wufanbao.api.demorabbitmq.test3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Test3Receiver {

    //@RabbitListener(bindings ={@QueueBinding(value = , exchange = @Exchange(value = "UserCouponEx61")) })
    //@RabbitListener(bindings = @QueueBinding(value = @Queue( value = "#{anonymousQueue.name}",durable = "true",autoDelete = "false"),exchange = @Exchange(value = "UserCouponEx61",type = "fanout")))
    @RabbitListener(queues = "#{anonymousQueue3.name}")
    public void process(String hello) {
        System.out.println("Receiver-all:" + hello);
    }
}
