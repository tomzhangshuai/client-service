package com.wufanbao.api.olddriverservice.controller.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue supplementCompletedQueue() {
        //队列名称，是否持久化
        //return new Queue("UserCouponEx6");
        return new AnonymousQueue();
    }

    @Bean
    FanoutExchange fanoutExchangeSupplementCompleted() {
        return new FanoutExchange("SupplementCompleted");
    }

    @Bean
    Binding bindingExchangeUserCouponEx6(Queue supplementCompletedQueue, FanoutExchange fanoutExchangeSupplementCompleted) {
        return BindingBuilder.bind(supplementCompletedQueue).to(fanoutExchangeSupplementCompleted);
    }


    @Bean
    public Queue machineSupplementFrontQueue() {
        //队列名称，是否持久化
        //return new Queue("MachineOrderFetch");
        return new AnonymousQueue();
    }

    @Bean
    FanoutExchange fanoutExchangeMachineSupplementFront() {
        return new FanoutExchange("MachineSupplementFront");
    }

    @Bean
    Binding bindingExchangeMachineOrderFetch(Queue machineSupplementFrontQueue, FanoutExchange fanoutExchangeMachineSupplementFront) {
        return BindingBuilder.bind(machineSupplementFrontQueue).to(fanoutExchangeMachineSupplementFront);
    }


    @Bean
    public Queue machineSupplementBehindQueue() {
        //队列名称，是否持久化
        //return new Queue("OrderLock");
        return new AnonymousQueue();
    }

    @Bean
    FanoutExchange fanoutMachineSupplementBehind() {
        return new FanoutExchange("MachineSupplementBehind");
    }

    @Bean
    Binding bindingExchangeOrderLock(Queue machineSupplementBehindQueue, FanoutExchange fanoutMachineSupplementBehind) {
        return BindingBuilder.bind(machineSupplementBehindQueue).to(fanoutMachineSupplementBehind);
    }


}
