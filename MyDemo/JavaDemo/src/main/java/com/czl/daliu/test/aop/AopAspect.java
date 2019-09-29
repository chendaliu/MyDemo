package com.czl.daliu.test.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopAspect {

    @Pointcut("execution (* com.czl.service.*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("before");
    }

    @Around("pointCut()")
    public void around() {
        System.out.println("around");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after");
    }
}
