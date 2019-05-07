package com.example.demomongo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.example.demomongo.mapper")
@SpringBootApplication
public class DemomongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomongoApplication.class, args);
    }

}

