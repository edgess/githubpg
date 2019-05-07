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
public class AuthorRoleCon {

    @Autowired
    JwtUtils jwtUtils;

    @Before("within(@org.springframework.web.bind.annotation.RestController *) && @annotation(authorRole)")
    public void requestLimit(final JoinPoint joinPoint, AuthorRole authorRole) throws Exception {
        //取token参数
        String token = joinPoint.getArgs()[1].toString();
        //转成list
        List<String> roles = (List<String>) jwtUtils.parseJWT(token).get("role");

//        for (int i = 0; i < aopedge.value().length; i++) {
//            System.out.println(aopedge.value()[i]);
//        }
//        System.out.println(aopedge.logical().name());

        switch (authorRole.logical().name()) {
            case "OR":
                boolean find = false;
                for (String aop : authorRole.value()) {
                    if (roles.contains(aop)) {
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    throw new AnnoRoleException();
                }
                break;
            case "AND":
                for (String aop : authorRole.value()) {
                    if (roles.contains(aop)) {
                    } else {
                        throw new AnnoRoleException();
                    }
                }
                break;
        }

    }
}