package com.example.and.interceptor;

import com.example.and.exception.PermissionException;
import com.example.and.mapper.UserMapper;
import com.example.and.token.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;


public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //获取请求路径
        String targetURL = request.getRequestURI().substring(request.getContextPath().length());

        //查询所有需要验证的路径集合
        Set<String> allPremissionURLs = userMapper.findAllPremissionUrl();

        // 权限验证,仅判断permission中设置过的
        if (allPremissionURLs.contains(targetURL)) {
            //判断当前用户是否拥有对应的权限
            //获取token中的username
            String token = request.getParameter("token");
            List<String> permissions = (List<String>) jwtUtils.parseJWT(token).get("permission");
            if (permissions.contains(targetURL)) {
                return true;
            } else {
                throw new PermissionException();
            }
        }
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