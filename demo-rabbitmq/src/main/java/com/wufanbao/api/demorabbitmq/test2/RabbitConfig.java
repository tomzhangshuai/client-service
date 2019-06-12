package com.wufanbao.api.demorabbitmq.test2;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue anonymousQueue() {
        return new AnonymousQueue();
    }

    @Bean
    FanoutExchange userCouponEx61() {
        return new FanoutExchange("UserCouponEx61");
    }


    @Bean
    Binding bindingExchangeUserCouponEx61(Queue anonymousQueue, FanoutExchange userCouponEx61) {
        return BindingBuilder.bind(anonymousQueue).to(userCouponEx61);
    }

}
