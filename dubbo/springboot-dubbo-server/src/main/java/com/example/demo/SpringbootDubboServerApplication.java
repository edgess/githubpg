package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ImportResource({ "classpath:dubbo.xml" })
//@ImportResource({ "classpath:edge.xml" })
@SpringBootApplication
public class SpringbootDubboServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDubboServerApplication.class, args);
		// System.in.read();

	}
}
