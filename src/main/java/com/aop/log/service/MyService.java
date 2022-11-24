package com.aop.log.service;

import com.aop.log.AspectAnnotation;

import java.util.List;

public class MyService {

    public void method1(List<String> list) throws InterruptedException {
        Thread.sleep(2000);
        list.add("method1");
        System.out.println("MyService method1 list.size=" + list.size());
        Thread.sleep(2000);
    }

    @AspectAnnotation
    public void method2() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("MyService method2");
        Thread.sleep(2000);
    }

    public boolean check() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("MyService check");
        Thread.sleep(2000);
        return true;
    }
}
