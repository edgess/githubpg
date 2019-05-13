package com.jiajia.mainslave;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
public class DynamicDataSourceAspect {

//    @Around("execution(public * com.jiajia..*.*(..))")
    @Around("within(@com.jiajia.server.OilService *) && @annotation(TargetDataSource)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aop");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        if(targetMethod.isAnnotationPresent(TargetDataSource.class)){
            String targetDataSource = targetMethod.getAnnotation(TargetDataSource.class).value() ;
            System.out.println(targetDataSource);
            DataSourceContextHolder.setDataSource(targetDataSource);
        }
        Object result = pjp.proceed();
        DataSourceContextHolder.clearDataSource();
        return result;
    }
}