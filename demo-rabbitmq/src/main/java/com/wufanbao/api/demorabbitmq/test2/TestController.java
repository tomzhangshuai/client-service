package com.wufanbao.api.demorabbitmq.test2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestSender testSender;

    @GetMapping("test")
    public void test() {
        testSender.sendA();
    }

}
