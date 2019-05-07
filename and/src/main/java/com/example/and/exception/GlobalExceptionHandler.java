package com.example.and.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

	@ExceptionHandler(value = AnnoRoleException.class)
	public String annoRoleExceptionHandler(Exception exception) throws Exception {
		Map<String, String> resultmap = new HashMap<>();
		resultmap.put("status", "error");
		resultmap.put("message", "无此角色");
		return JSONObject.toJSONString(resultmap);
	}

	@ExceptionHandler(value = PermissionException.class)
	public String permissionExceptionHandler(Exception exception) throws Exception {
		Map<String, String> resultmap = new HashMap<>();
		resultmap.put("status", "error");
		resultmap.put("message", "无此权限");
		return JSONObject.toJSONString(resultmap);
	}

	@ExceptionHandler(value = TokenValidateException.class)
	public String tokenValidateExceptionHandler(Exception exception) throws Exception {
		Map<String, String> resultmap = new HashMap<>();
		resultmap.put("status", "error");
		resultmap.put("message", "凭证错误");
		return JSONObject.toJSONString(resultmap);
	}

}