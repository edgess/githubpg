package com.example.demo;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*", filterName = "myfilter")
public class MyFilter implements Filter {
	@Override
	public void destroy() {
		System.out.println("<----Filter destroy---->");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("<----Filter doFilter---->");
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("<----Filter init---->");
	}
}
