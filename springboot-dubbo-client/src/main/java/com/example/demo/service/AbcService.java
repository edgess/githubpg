package com.example.demo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.demo.DemoService;

import org.springframework.stereotype.Component;

@Component
public class AbcService {
	@Reference(version = "1.0.0")
	public DemoService demoService;
}