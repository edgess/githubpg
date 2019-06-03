package com.example.demo1.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

public class FrequencyInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger("HandlerInterceptor");
	private Map<String, Integer> redisTemplate = new HashMap<String, Integer>();
	// 执行任务前的延迟时间，单位是毫秒
	public static final Integer TIME = 60000;
	// 次数阀值
	public static final Integer COUNT = 10;

	/**
	 * controller 执行之前调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String ip = request.getLocalAddr();
		String url = request.getRequestURL().toString();
		String key = "req_limit_".concat(url).concat(ip);
//		System.out.println(key);
		if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
			redisTemplate.put(key, 1);
		} else {
			redisTemplate.put(key, redisTemplate.get(key) + 1);
		}
		int count = redisTemplate.get(key);
		if (count > 0) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() { // 创建一个新的计时器任务。
				@Override
				public void run() {
					redisTemplate.remove(key);
				}
			};
			// 安排在指定延迟后执行指定的任务
			timer.schedule(task, FrequencyInterceptor.TIME);
		}
//		System.out.println(count);
		if (count > FrequencyInterceptor.COUNT) {
			logger.info("用户IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + FrequencyInterceptor.COUNT + "]");
			PrintWriter out = response.getWriter();
			JSONObject jsob = new JSONObject();
			jsob.put("status", "102");
			out.append(jsob.toString());
			return false;
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