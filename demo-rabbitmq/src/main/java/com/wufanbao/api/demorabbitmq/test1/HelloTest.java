package com.wufanbao.api.demorabbitmq.test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTest {
    @Autowired
    private HelloSender helloSender;

    @GetMapping("hello")
    public void hello() {
        helloSender.sendA();
    }

    @GetMapping("hello1")
    public void hello1() {
        helloSender.sendB();
    }
}
