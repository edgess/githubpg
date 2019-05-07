package com.example.and.aop;

import com.example.and.exception.AnnoRoleException;
import com.example.and.token.JwtUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class AuthorRoleNotCon {
    @Autowired
    JwtUtils jwtUtils;

    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(authorRoleNot)")
    public void requestLimit(final JoinPoint joinPoint, AuthorRoleNot authorRoleNot) throws Exception {
        //取token参数
        String token = joinPoint.getArgs()[1].toString();
        //转成list
        List<String> roles = (List<String>) jwtUtils.parseJWT(token).get("role");

        boolean find = false;
        for (String aop : authorRoleNot.value()) {
            if (roles.contains(aop)) {
                find = true;
                break;
            }
        }
        if (find) {
            throw new AnnoRoleException();
        }


    }
}