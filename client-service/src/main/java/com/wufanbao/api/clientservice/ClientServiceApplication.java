package com.wufanbao.api.clientservice;

import com.wufanbao.api.clientservice.runner.ApplicationContextProvider;
import com.wufanbao.api.clientservice.runner.UserOrderRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@MapperScan("com.wufanbao.api.clientservice.dao")
@ImportResource(locations = {"classpath:spring-redis.xml"})

/*public class ClientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }
}*/

public class ClientServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(ClientServiceApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}
}

/*
100:正常
101:错误（异常），返回数据
102:失败，返回数据
103:签名错误
104:登录失效
1001:当前城市未开发
1002:支付密码没有设置
1003:支付密码错误
1004:优惠券过期
1005:支付密码错误超过3次
1006:扫码取餐的二维码过期，重新扫码
1007:积分不足
1008:第三方登录绑定手机
*/

/*
数据库改动：
UserOrder 表，增加ImageUrl
userordercapital 默认值
 */

/**
 * CapitalType
 * 微信：2
 * 支付宝：3
 * 余下
 * 家庭付款:5
 * 企业付款：7
 * 优惠券：9
 */
