package com.example.model3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Model3Application {

	public static void main(String[] args) {
		SpringApplication.run(Model3Application.class, args);
	}

}
