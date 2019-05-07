package com.example.and.interceptor;

import com.example.and.exception.TokenValidateException;
import com.example.and.token.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ConfigurationProperties(prefix = "loginignore")
public class TokenInterceptor implements HandlerInterceptor {
//    //获取不需要拦截的url
//    private List<String> ignoreURLs = new ArrayList<>();

//    public void setIgnoreURLs(List<String> ignoreURLs) {
//        this.ignoreURLs = ignoreURLs;
//    }

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //获取请求路径
        String targetURL = request.getRequestURI().substring(request.getContextPath().length());

        //匹配
//        if (ignoreURLs.contains(targetURL)) {
//        } else {

        //获取token
        String token = request.getParameter("token");
        //判断token，未通过的抛异常
        if (null == token || !jwtUtils.validateJWT(token)) {
            throw new TokenValidateException();
        }

//        }
        return true;
    }


    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // System.out.println("------postHandle-----");
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // System.out.println("------afterCompletion-----");
    }
}