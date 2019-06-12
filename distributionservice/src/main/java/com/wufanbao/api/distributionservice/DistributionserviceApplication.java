package com.wufanbao.api.distributionservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.wufanbao.api.distributionservice.dao")
@ImportResource(locations = {"classpath:spring-redis.xml"})
public class DistributionserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributionserviceApplication.class, args);
    }

}

