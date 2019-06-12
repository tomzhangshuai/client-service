package com.wufanbao.api.demorabbitmq.test1;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 队列配置
 */

@Configuration
public class RibbitConfig {

    @Bean
    public Queue AMessage() {
        return new AnonymousQueue();
    }
    @Bean
    public Queue BMessage() {
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeUserCouponEx62() {
        return new FanoutExchange("UserCouponEx62");
    }
    @Bean
    FanoutExchange fanoutExchangeUserCouponEx63() {
        return new FanoutExchange("UserCouponEx63");
    }
    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchangeUserCouponEx62) {
        return BindingBuilder.bind(AMessage).to(fanoutExchangeUserCouponEx62);
    }
    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchangeUserCouponEx62) {
        return BindingBuilder.bind(BMessage).to(fanoutExchangeUserCouponEx62);
    }
}
