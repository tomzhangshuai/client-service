package com.wufanbao.logclient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private static Logger log = LogManager.getLogger(IndexController.class);


    @GetMapping("/")
    public String Test() {
        for (int i = 0; i < 100; i++) {
            log.warn("logger warn");
            log.debug("logger debug");
            log.info("bar logger info");
            log.debug("bar logger debug long long ");
        }
        return "test client";
    }
}
