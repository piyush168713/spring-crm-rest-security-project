package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
@Aspect
public class CRMLoggingAspect {

    // setup logger
    private final Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    // add @Before Advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        String message = "Executing @Before =====>";
        logger.info(message + theMethod);

        // display the arguments
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(arg -> logger.info("===>> Argument: "
                + arg.toString()));
    }

    // add @AfterReturning Advice
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // display method we are calling
        String theMethod = joinPoint.getSignature().toShortString();
        String message = "Executing @AfterReturning =====>";
        logger.info(message + theMethod);

        // display the data result
        logger.info("====>>> result: " + result);
    }
}
