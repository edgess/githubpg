package com.example.demo1.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	/**
	 * 注册 拦截器 单独的servlet 将不会被过滤
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new CORSInterceptor());
//		registry.addInterceptor(new FrequencyInterceptor());
//		registry.addInterceptor(new TokenInterceptor());
		super.addInterceptors(registry);
	}

}
