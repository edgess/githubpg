package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.service.AbcService;

@SpringBootApplication
public class SpringbootDubboClientApplication {

	public static void main(String[] args) {
		// SpringApplication.run(SpringbootDubboClientApplication.class, args);

		ConfigurableApplicationContext run = SpringApplication.run(SpringbootDubboClientApplication.class, args);
		AbcService bean = run.getBean(AbcService.class);
		System.out.println(bean.demoService.sayHello("edge"));

	}
}
