package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {
    @Autowired
    private ClientSetting clientSetting;

    @Autowired
    private AmqpTemplate amqpTemplate;


    @GetMapping("version")
    public String version() {
        amqpTemplate.convertAndSend("ZhaoJingTest", "", String.valueOf("12312312").getBytes());
        amqpTemplate.convertAndSend("MachineOrderFetch", "", String.valueOf("ddddd").getBytes());

        //amqpTemplate.send();
        return clientSetting.getVersion();
    }
}
