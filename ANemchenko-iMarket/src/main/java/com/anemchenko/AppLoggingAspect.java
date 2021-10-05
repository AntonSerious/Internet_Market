package com.anemchenko;

import com.anemchenko.utils.StatisticService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AppLoggingAspect {
      private final StatisticService statisticService;

      //    @Before("execution(public * com.anemchenko.controllers.AuthController.*(..))")
//    public void beforeUserAuth(JoinPoint joinPoint){
//        System.out.println("аутентификация нового пользователя");
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        System.out.println(" сигнатура: " + methodSignature);
//
//        Object[] args = joinPoint.getArgs();
//        if(args.length > 0){
//            System.out.println("arguments: ");
//            for(Object o : args){
//                System.out.println(o);
//            }
//        }
//    }

      @Around("execution(public * com.anemchenko.services.*.*(..))")
      public Object serviceLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
            long begin = System.currentTimeMillis();
            String serviceName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
            Object out = proceedingJoinPoint.proceed();
            long end = System.currentTimeMillis();
            long duration = end - begin;
            statisticService.addNewStat(serviceName, duration);
            return out;
      }
}
