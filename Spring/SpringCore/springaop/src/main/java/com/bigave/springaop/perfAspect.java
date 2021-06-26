package com.bigave.springaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class perfAspect {
    // Value에는 Pointcut 이름 또는 Pointcut 정의.
//    @Around("execution(* com.bigave..*.EventService.*(..))") //com.bigave 패키지 밑에있는 모든 클래스 중에서 EventService안에 들어있는 모든 메소드에 아래 메서드(행위)를 적용하라.
    @Around("@annotation(PerfLogging)")
    public Object legPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();

        Object retVal = pjp.proceed();

        System.out.println(System.currentTimeMillis() - begin);
        return retVal;
    }

    @Before("bean(simpleEventService)") // 지정된 메소드의 실행 전에.
    public void hello(){
        System.out.println("hello");
    }
}
