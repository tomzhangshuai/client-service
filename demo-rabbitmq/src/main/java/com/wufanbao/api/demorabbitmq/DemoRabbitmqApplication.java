package com.wufanbao.api.demorabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Configuration
@SpringBootApplication
public class DemoRabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoRabbitmqApplication.class, args);
    }
}
