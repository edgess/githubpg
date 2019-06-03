package com.example.demo1.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.token.TokenMgr;

public class TokenInterceptor implements HandlerInterceptor {

	/**
	 * controller 执行之前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ctxPath = request.getContextPath();
		String currentURL = request.getRequestURI();
		String targetURL = currentURL.substring(ctxPath.length());
		// 拦截非login请求
		if (!"/login".equals(targetURL)) {
			// 判读token，未通过发送错误json
			if (null == request.getParameter("token") || !TokenMgr.validateJWT(request.getParameter("token"))) {
				response.addHeader("Content-Type", "text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				JSONObject jsob = new JSONObject();
				jsob.put("status", "101");
				out.append(jsob.toString());
				return false;
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