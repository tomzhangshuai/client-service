package com.wufanbao.api.clientservice.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//定义切面
@Aspect
//@Component
public class LogServiceTakeTime {
    private final static Logger log = LoggerFactory.getLogger(LogServiceTakeTime.class);

    //定义切点
    @Pointcut("execution(* com.wufanbao.api.clientservice.service..*.*(..))")
    public void performance() {
    }

    //在切入点前后执行的方法
    @Around("performance()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录起始时间
        long begin = System.currentTimeMillis();
        Object result = "";
        /** 执行目标方法 */
        try {
            result = joinPoint.proceed();
        } finally {
            long took = System.currentTimeMillis() - begin;
            String method = joinPoint.getSignature().toShortString();
            String methodName = joinPoint.getSignature().getName();
            String str = methodName + ":" + method + ":执行时间为:";
            if (took >= 10000) {
                log.error(str + "{}ms", took);
            } else if (took >= 5000) {
                log.warn(str + "{}ms", took);
            } else if (took >= 3000) {
                log.info(str + "{}ms", took);
            } else {
                log.info(str + "{}ms", took);
            }
        }
        return result;
    }

    //切入点开始执行前处理的方法
    @Before("performance()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        log.info("doBefore");
    }

    //在切入点return数据后执行的方法(一般用于对返回数据的包装)
    @AfterReturning(returning = "ret", pointcut = "performance()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("doAfterReturning");
    }
}
