package com.wufanbao.api.oldclientservice.Tool;

/**
 * @program: alphaWuFan
 * @description: 自定义注解
 * @author: Wang Zhiyuan
 * @create: 2018-03-08 11:45
 **/

import java.lang.annotation.*;

/**
 * Created by wangH on 2017/10/24.
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestValidationCode {

}
