package com.example.and;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.and.mapper")
@SpringBootApplication
public class AndApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndApplication.class, args);
	}

}

