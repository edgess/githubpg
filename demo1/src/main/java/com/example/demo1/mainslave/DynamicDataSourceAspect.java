package com.example.demo1.mainslave;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {


    @Around("execution(* com.example.demo1.services.*Service.*(..))")
//    @Around("within(@com.example.demo1.services.OilService *) && @annotation(TargetDataSource)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        if (targetMethod.isAnnotationPresent(TargetDataSource.class)) {
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).value();
            DataSourceContextHolder.setDataSource(targetDataSource);
        }
        Object result = pjp.proceed();
        DataSourceContextHolder.clearDataSource();
        return result;
    }
}