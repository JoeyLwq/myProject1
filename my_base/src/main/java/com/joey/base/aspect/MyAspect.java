package com.joey.base.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    @Before("execution(* *.*getAll(..))")
    private void tryA(){
        System.out.println("试一下spring切面");
    }
}
