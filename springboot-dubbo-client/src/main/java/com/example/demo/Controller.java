package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.AbcService;

@RestController
public class Controller {
	@Autowired
	public AbcService abcService;

	@RequestMapping("say")
	public String say(String name) {
		return abcService.demoService.sayHello(name);
	}

}