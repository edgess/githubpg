package com.example.demo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.demo.DemoService;

@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
