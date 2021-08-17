package com.shustrov38.cargocontrolservice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogExecutionTimeAdvice {

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch timer = new StopWatch();

        timer.start();
        Object proceed = joinPoint.proceed();
        timer.stop();

        System.out.println(joinPoint.getSignature() + " executed in " + timer.getTotalTimeMillis() + " ms (" + timer.getTotalTimeNanos() + " nanos)");
        return proceed;
    }
}
