package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Sample1Aspect {

    private final Logger LOGGER = LoggerFactory.getLogger(Sample1Aspect.class);

    @Pointcut("execution(* com.example.aop.SampleComponent.concatTwoString(..))")
    void concat() {
    }

    @Pointcut("execution(* com.example.aop.SampleComponent.*(..))")
    void allSampleComponentMethods() {
    }

    @Pointcut("execution(* com.example.aop.SampleComponent.echoSomething(..))")
    void echoMethod() {
    }

    @Around("concat() && args(s1, s2)")
    Object aroundConcat(ProceedingJoinPoint pjp, String s1, String s2) throws Throwable {
        LOGGER.info("Concat method was called with arguments {}, {}. ", s1, s2);

        String m1 = "(" + s1 + ")";
        String m2 = "(" + s2 + ")";
        Object result = pjp.proceed(new Object[]{m1, m2});
        return "[" + result + "]";
    }

/*    @AfterThrowing(pointcut = "allSampleComponentMethods()", throwing = "ex")
    void afterThrowing(JoinPoint joinPoint,Exception ex ){

        LOGGER.info("After the method {} boomed. Exception is {}.", joinPoint.getSignature(), ex.getClass().getName());
    }*/

/*   @After("echoMethod()")
    void afterEcho(){
        LOGGER.info("After echo!");
    }*/

    // @Before("allSampleComponentMethods()")
    //  public void beforeEachMethod(JoinPoint joinPoint) {
    //  LOGGER.info("Before calling a method {}.", joinPoint.getSignature());

    //  }

}
