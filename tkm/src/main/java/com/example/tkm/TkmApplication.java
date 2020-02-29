package com.example.tkm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.tkm.test.dao","com.example.tkm.daotkmapper" })
public class TkmApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkmApplication.class, args);
    }

}
