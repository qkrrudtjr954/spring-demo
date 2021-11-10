package com.demo.demoproject.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* com.demo..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long s = System.currentTimeMillis();

        try {
            System.out.println("START: " + s);
            return joinPoint.proceed();
        } finally {
            long f = System.currentTimeMillis();
            long timeMs = f - s;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }


    }
}
