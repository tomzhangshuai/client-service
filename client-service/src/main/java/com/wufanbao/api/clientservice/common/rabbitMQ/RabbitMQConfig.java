package com.wufanbao.api.clientservice.common.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout交换机发送消息，绑定了这个交换机的所有队列都收到这个消息。
 */
@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue userCouponEx6Queue() {
        //队列名称，是否持久化
        //return new Queue("UserCouponEx6");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeUserCouponEx6() {
        return new FanoutExchange("UserCouponEx6");
    }
    @Bean
    Binding bindingExchangeUserCouponEx6(Queue userCouponEx6Queue, FanoutExchange fanoutExchangeUserCouponEx6) {
        return BindingBuilder.bind(userCouponEx6Queue).to(fanoutExchangeUserCouponEx6);
    }
    @Bean
    public Queue machineOrderFetchQueue() {
        //队列名称，是否持久化
        //return new Queue("MachineOrderFetch");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeMachineOrderFetch() {
        return new FanoutExchange("MachineOrderFetch");
    }
    @Bean
    Binding bindingExchangeMachineOrderFetch(Queue machineOrderFetchQueue, FanoutExchange fanoutExchangeMachineOrderFetch) {
        return BindingBuilder.bind(machineOrderFetchQueue).to(fanoutExchangeMachineOrderFetch);
    }
    @Bean
    public Queue sendMachineOrderFetchQueue() {
        return new AnonymousQueue();
    }
    @Bean
    public FanoutExchange fanoutExchangeSendMachineOrderFetch() {
        return new FanoutExchange("SendMachineOrderFetch");
    }
    @Bean
    public Binding bindingExchangeSendMachineOrderFetch(Queue sendMachineOrderFetchQueue, FanoutExchange fanoutExchangeSendMachineOrderFetch) {
        return BindingBuilder.bind(sendMachineOrderFetchQueue).to(fanoutExchangeSendMachineOrderFetch);
    }
    @Bean
    public Queue orderLockQueue() {
        //队列名称，是否持久化
        //return new Queue("OrderLock");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeOrderLock() {
        return new FanoutExchange("OrderLock");
    }
    @Bean
    Binding bindingExchangeOrderLock(Queue orderLockQueue, FanoutExchange fanoutExchangeOrderLock) {
        return BindingBuilder.bind(orderLockQueue).to(fanoutExchangeOrderLock);
    }
    @Bean
    public Queue orderUnLockQueue() {
        //队列名称，是否持久化
        //return new Queue("OrderUnLock");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeOrderUnLock() {
        return new FanoutExchange("OrderUnLock");
    }
    @Bean
    Binding bindingExchangeOrderUnLock(Queue orderUnLockQueue, FanoutExchange fanoutExchangeOrderUnLock) {
        return BindingBuilder.bind(orderUnLockQueue).to(fanoutExchangeOrderUnLock);
    }

    @Bean
    public Queue productOutQueue() {
        //队列名称，是否持久化
        //return new Queue("ProductOut");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeProductOut() {
        return new FanoutExchange("ProductOut");
    }
    @Bean
    Binding bindingExchangeProductOut(Queue productOutQueue, FanoutExchange fanoutExchangeProductOut) {
        return BindingBuilder.bind(productOutQueue).to(fanoutExchangeProductOut);
    }

    @Bean
    public Queue userFisrtOrderQueue() {
        //队列名称，是否持久化
        //return new Queue("UserFisrtOrder");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeUserFisrtOrder() {
        return new FanoutExchange("UserFisrtOrder");
    }
    @Bean
    Binding bindingExchangeUserFisrtOrder(Queue userFisrtOrderQueue, FanoutExchange fanoutExchangeUserFisrtOrder) {
        return BindingBuilder.bind(userFisrtOrderQueue).to(fanoutExchangeUserFisrtOrder);
    }

    @Bean
    public Queue userOrderEx6Queue() {
        //队列名称，是否持久化
        //return new Queue("UserOrderEx6");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeUserOrderEx6() {
        return new FanoutExchange("UserO   rderEx6");
    }
    @Bean
    Binding bindingExchangeUserOrderEx6(Queue userOrderEx6Queue, FanoutExchange fanoutExchangeUserOrderEx6) {
        return BindingBuilder.bind(userOrderEx6Queue).to(fanoutExchangeUserOrderEx6);
    }

    @Bean
    public Queue zhangShuaiTestQueue() {
        //队列名称，是否持久化
        //return new Queue("UserOrderEx6");
        //return new AnonymousQueue();
        return new Queue("ZhaoJingTest");
    }
    @Bean
    FanoutExchange fanoutExchangeZhangShuaiTest() {
        return new FanoutExchange("zhangShuaiTestQueue");
    }
    @Bean
    Binding bindingExchangeZhaojingTest(Queue zhangShuaiTestQueue, FanoutExchange fanoutExchangeZhangShuaiTest) {
        return BindingBuilder.bind(zhangShuaiTestQueue).to(fanoutExchangeZhangShuaiTest);
    }
    @Bean
    public Queue OrderFetchCompletedQueue() {
        //队列名称，是否持久化
        //return new Queue("UserCouponEx6");
        return new AnonymousQueue();
    }
    @Bean
    FanoutExchange fanoutExchangeOrderFetchCompleted() {
        return new FanoutExchange("OrderFetchCompleted");
    }
    @Bean
    Binding bindingExchangeOrderFetchCompleted(Queue OrderFetchCompletedQueue, FanoutExchange fanoutExchangeOrderFetchCompleted) {
        return BindingBuilder.bind(OrderFetchCompletedQueue).to(fanoutExchangeOrderFetchCompleted);
    }

    @Bean
    public Queue userpayBindQueue(){
        return new AnonymousQueue();
    }
    @Bean
    public FanoutExchange fanoutExchangeUserpayBind(){
        return new FanoutExchange("userpayBind");
    }
    @Bean
    Binding bindingExchangeUserpayBind(Queue userpayBindQueue,FanoutExchange fanoutExchangeUserpayBind ){
        return BindingBuilder.bind(userpayBindQueue).to(fanoutExchangeUserpayBind);
    }
    @Bean
    public Queue userpayUnBindQueue(){
        return new AnonymousQueue();
    }
    @Bean
    public FanoutExchange fanoutExchangeUserpayUnBind(){
        return new FanoutExchange("userpayUnBind");
    }
    @Bean
    Binding bindingExchangeUserpayUnBind(Queue userpayUnBindQueue,FanoutExchange fanoutExchangeUserpayUnBind){
        return BindingBuilder.bind(userpayUnBindQueue).to(fanoutExchangeUserpayUnBind);
    }
}
