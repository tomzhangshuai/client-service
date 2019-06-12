package com.wufanbao.api.oldclientservice;

import com.wufanbao.api.oldclientservice.task.ApplicationContextProvider;
import com.wufanbao.api.oldclientservice.task.UserOrderRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@MapperScan("com.wufanbao.api.oldclientservice.dao")
@ImportResource(locations = {"classpath:spring-redis.xml"})
//public class OldClientServiceApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(OldClientServiceApplication.class, args);
//
//		//UserOrderRunner userOrderRunner = (UserOrderRunner) ApplicationContextProvider.getBean("UserOrderRunner", UserOrderRunner.class);
//		//userOrderRunner.start();
//
//	}
//}

public class OldClientServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OldClientServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OldClientServiceApplication.class, args);

        //UserOrderRunner userOrderRunner = ApplicationContextProvider.getBean("UserOrderRunner", UserOrderRunner.class);
        //userOrderRunner.start();
    }
}

