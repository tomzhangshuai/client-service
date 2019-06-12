package com.wufanbao.api.demorabbitmq.test3;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig3 {

    @Bean
    public Queue anonymousQueue3() {
        return new AnonymousQueue();
    }

    @Bean
    FanoutExchange userCouponEx62() {
        return new FanoutExchange("UserCouponEx62");
    }


    @Bean
    Binding bindingExchangeUserCouponEx62(Queue anonymousQueue3, FanoutExchange userCouponEx62) {
        return BindingBuilder.bind(anonymousQueue3).to(userCouponEx62);
    }

}
