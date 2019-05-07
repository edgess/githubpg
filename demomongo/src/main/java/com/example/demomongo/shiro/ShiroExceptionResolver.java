package com.example.demomongo.shiro;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ShiroExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if (ex instanceof org.apache.shiro.authc.IncorrectCredentialsException) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("name", "密码错误");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (ex instanceof org.apache.shiro.authc.UnknownAccountException) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("name", "无此账号");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (ex instanceof org.apache.shiro.authc.LockedAccountException) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("name", "账号锁定");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (ex instanceof org.apache.shiro.authc.ExpiredCredentialsException) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("name", "账号过期");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (ex instanceof org.apache.shiro.authc.ExcessiveAttemptsException) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("name", "过多尝试");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        if (ex instanceof org.apache.shiro.authz.AuthorizationException) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("name", "无此权限");
            modelAndView.setViewName("error");
            return modelAndView;
        }


        // CustomException customException = null;
        //
        // // 如果抛出的是系统自定义的异常则直接转换
        // if (ex instanceof CustomException) {
        // customException = (CustomException) ex;
        // } else {
        // // 如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
        // // 这里我就也有CustomException省事了，实际中应该要再定义一个新的异常
        // customException = new CustomException("系统未知错误");
        // }
        //
        // 向前台返回错误信息
        return null;
    }

}
