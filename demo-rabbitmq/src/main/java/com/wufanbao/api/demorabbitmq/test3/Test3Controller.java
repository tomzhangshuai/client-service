package com.wufanbao.api.demorabbitmq.test3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test3Controller {
    @Autowired
    private Test3Sender testSender;

    @GetMapping("test3")
    public void test() {
        testSender.sendA();
    }

}
