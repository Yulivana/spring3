package com.aop.security.aspect;

import com.aop.security.model.User;
import com.aop.security.service.SecurityService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Aspect
public class SecurityAspect {

    SecurityService securityService = new SecurityService();

    @Pointcut("@annotation(com.aop.security.SecurityAnnotation) && args(user,..)")
    public void callAtMyServiceSecurityAnnotation(User user) { }

    @Pointcut("execution(public * com.aop.security.service.MyService.*(..))")
    public void callAtMyServicePublic() {
    }

    @Around("callAtMyServicePublic()")
    public Object aroundCallAt(ProceedingJoinPoint call) throws Throwable {
        StopWatch clock = new StopWatch(call.toString());
        try {
            clock.start(call.toShortString());
            return call.proceed();
        } finally {
            clock.stop();
            System.out.println(clock.prettyPrint());
        }
    }

    @Around("callAtMyServiceSecurityAnnotation(user)")
    public Object aroundCallAt(ProceedingJoinPoint pjp, User user) {
        Object retVal = null;
        if (securityService.checkRight(user)) {
            try {
                retVal = pjp.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return retVal;
    }
}
