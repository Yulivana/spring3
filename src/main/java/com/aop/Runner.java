package com.aop;

import com.aop.log.service.MyService;
import com.aop.security.config.MyConfig;
import com.aop.security.model.Balance;
import com.aop.security.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Runner {


    public static void main(String[] args) throws InterruptedException {
       // aopXml();
       // aopAnnotation();
      //  aopLogging();
      //  aopLogging2();
      //  aopSecurity();
    }

    public static void aopXml(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop-developer-config.xml");
        Developer developer = (Developer) context.getBean("developer");

        System.out.println(developer);
        developer.throwSomeMysticException();
    }

    public static void aopAnnotation(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop-developer-annotations-config.xml");
        Developer developer = (Developer) context.getBean("developer");

        System.out.println(developer);
        developer.throwSomeMysticException();
    }

    public static void aopLogging() throws InterruptedException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop-logging.xml");
        MyService myService = (MyService) context.getBean("myService");
        List<String> list = new ArrayList<>();
        list.add("test1");
        myService.method1(list);
        myService.method2();
    }

    public static void aopLogging2() throws InterruptedException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop-logging.xml");
        MyService myService = (MyService) context.getBean("myService");
        myService.method2();
        myService.check();

        myService.method1(new ArrayList<>());
    }

    public static void aopSecurity() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("aop-security.xml");
       // ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        com.aop.security.service.MyService myService = (com.aop.security.service.MyService) context.getBean("myService");
       // com.aop.security.service.MyService myService = context.getBean(com.aop.security.service.MyService.class);
        User user = new User();
        user.setId(1L);
        user.setName("User1");
        user.setRole("user");

        Balance balance = myService.getAccountBalance(user);
        if(balance == null) {
            System.out.println("accessDenied");
        } else {
            System.out.println(balance.getBalance());
        }


    }
}
