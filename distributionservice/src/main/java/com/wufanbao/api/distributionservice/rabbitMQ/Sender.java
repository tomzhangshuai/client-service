package com.wufanbao.api.distributionservice.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 配送完成
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    public void supplementCompleted(Object message) throws IOException, TimeoutException {
        amqpTemplate.convertAndSend("SupplementCompleted", "", String.valueOf(message).getBytes());
    }

    /**
     * 开前仓消息
     *
     * @param message
     * @return void
     * @date 2018/7/3
     */
    public void openMachineFront(Object message) throws IOException, TimeoutException {
        amqpTemplate.convertAndSend("MachineSupplementFront", "", String.valueOf(message).getBytes());
    }

    /**
     * 开后仓消息
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    public void openMachineBehind(Object message) throws IOException, TimeoutException {
        amqpTemplate.convertAndSend("MachineSupplementBehind", "", String.valueOf(message).getBytes());
    }
}
