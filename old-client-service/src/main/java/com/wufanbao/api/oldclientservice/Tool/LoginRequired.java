package com.wufanbao.api.oldclientservice.Tool;

/**
 * alphaWuFan
 * 自定义注解
 *
 * @author Wang Zhiyuan
 * @create 2018-03-08 11:45
 **/

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
