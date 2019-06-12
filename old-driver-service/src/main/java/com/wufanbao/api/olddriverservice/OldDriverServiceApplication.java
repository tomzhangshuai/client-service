package com.wufanbao.api.olddriverservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan("com.wufanbao.api.olddriverservice.dao")
@ComponentScan("com.wufanbao.api.olddriverservice.controller")
@ImportResource(locations = {"classpath:spring-redis.xml"})
//public class OldDriverServiceApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(OldDriverServiceApplication.class, args);
//	}
//}
public class OldDriverServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OldDriverServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OldDriverServiceApplication.class, args);
    }
}

