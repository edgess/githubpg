package com.example.and.aop;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
// 最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface AuthorRoleNot {
	String[] value() default "";
}