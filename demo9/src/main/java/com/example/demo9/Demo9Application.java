package com.example.demo9;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo9.mapper")
public class Demo9Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo9Application.class, args);
    }

}

