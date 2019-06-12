package com.wufanbao.api.distributionservice.returnValueHandles;

import java.lang.annotation.*;


@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreReturnHandler {
    String value() default "";
}
