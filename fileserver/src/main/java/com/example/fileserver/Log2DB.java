package com.example.fileserver;

import com.edge.dao.server.Log2Service;
import com.edge.entity.Log2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class Log2DB implements HandlerInterceptor {
    @Autowired
    Log2Service log2Service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(new Date().toString() + "---" + request.getRemoteAddr());
//        System.out.println(  request.getRequestURI() + "--" + request.getPathInfo() + "--" + request.getPathTranslated() + "--" + request.getQueryString());
        Log2 log2 = new Log2();
        log2.setDate(new Date());
        log2.setRemoteaddr(request.getRemoteAddr());
        log2.setCode(request.getQueryString());
        log2.setName(request.getRequestURI());
        log2Service.insertlog2(log2);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
