package com.wufanbao.api.demorabbitmq.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableScheduling
public class TestController {

    @Scheduled(cron ="0/5 * * * * ? ")
    public void send1(String message){



    }

}
