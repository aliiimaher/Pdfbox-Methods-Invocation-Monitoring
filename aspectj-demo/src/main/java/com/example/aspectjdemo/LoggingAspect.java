package com.example.aspectjdemo;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
    @Before("execution(* com.example.aspectjdemo.EmployeeService.addEmployee(..))")
    public void logBefore() {
        System.out.println("LoggingAspect: Before adding employee.");
    }

    @After("execution(* com.example.aspectjdemo.EmployeeService.addEmployee(..))")
    public void logAfter() {
        System.out.println("LoggingAspect: After adding employee.");
    }
}
