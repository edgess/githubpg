package com.mav.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mav.token.TokenMgr;

public class MyFilter implements Filter {
	// public static final String login_page = "./index.jsp";
	public static final String logout_page = "/login.jsp";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String ctxPath = request.getContextPath();
		String currentURL = request.getRequestURI();
		// 除掉项目名称时访问页面当前路径
		String targetURL = currentURL.substring(ctxPath.length());
		// HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		// 对当前页面进行判断，如果当前页面不为登录页面
		if (!("/login.jsp".equals(targetURL)) && (cookies != null)) {
			// 在不为登陆页面时，再进行判断，如果不是登陆页面也没有session则跳转到登录页面，
			// if (session == null || session.getAttribute("flag") == null) {
			// response.sendRedirect(logout_page);
			// return;
			// } else {
			// // 这里表示正确，会去寻找下一个链，如果不存在，则进行正常的页面跳转
			// filterChain.doFilter(request, response);
			// return;
			// }

			for (Cookie cookie : cookies) {
				String loginInfo = cookie.getValue();
				if (cookie.getName().equals("jwt") && TokenMgr.validateJWT(loginInfo).isSuccess()) {
					filterChain.doFilter(request, response);
					return;
				}
			}
			response.sendRedirect(ctxPath + logout_page);
		}

		// 这里表示如果当前页面是登陆页面，跳转到登陆页面
		filterChain.doFilter(request, response);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
