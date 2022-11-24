package com.aop.log.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Aspect
@Order(2)
public class SecondAspect {
    private Logger logger = Logger.getLogger(MyAspect.class.getName());


    @Pointcut("@annotation(com.aop.log.AspectAnnotation)")
    public void callAtMyServiceAnnotation() { }

    @Pointcut("execution(* com.aop.log.service.MyService.method1(..)) && args(list,..))")
    public void callAtMyServiceMethod1(List<String> list) { }

    @Pointcut("execution(* com.aop.log.service.MyService.check())")
    public void callAtMyServiceAfterReturning() { }

    @AfterReturning(pointcut="callAtMyServiceAfterReturning()", returning="retVal")
    public void afterReturningCallAt(JoinPoint jp, boolean retVal) {
        logger.info("SecondAspect: AfterReturning  "+ jp.toString() + " return " + retVal);
    }


    @Before("callAtMyServiceMethod1(list)")
    public void beforeCallAtMethod1(List<String> list) {
        logger.info("SecondAspect: before Method1 = " + list.size());
    }

    @Before("callAtMyServiceAnnotation()")
    public void beforeCallAt(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        logger.info("SecondAspect: before " + jp.toString() + ", args=[" + args + "]");
    }

    @After("callAtMyServiceAnnotation()")
    public void afterCallAt(JoinPoint jp) {
        logger.info("SecondAspect: after " + jp.toString());
    }

}
