package com.wufanbao.api.distributionservice.resolvers;

import java.lang.annotation.*;


@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface User {
    String value() default "";
}
