package com.aop.security.config;

import com.aop.security.aspect.SecurityAspect;
import com.aop.security.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class MyConfig {

    @Bean
    public MyService getMyService(){
        return new MyService();
    }

    @Bean
    public SecurityAspect getSecurityAspect(){
        return new SecurityAspect();
    }
}
