package com.mav.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mav.token.TokenMgr;

public class MyInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// if (request.getSession(false) == null ||
		// !request.getSession().getAttribute("flag").equals("admin")) {
		// response.sendRedirect(request.getContextPath() + "/login.jsp");
		// }
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				String loginInfo = cookie.getValue();
				if (cookie.getName().equals("jwt") && TokenMgr.validateJWT(loginInfo).isSuccess()) {
					return true;
				}
			}
		}
		System.out.println("===========HandlerInterceptor1 preHandle");
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// System.out.println("===========HandlerInterceptor1 postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// System.out.println("===========HandlerInterceptor1 afterCompletion");
	}

}
