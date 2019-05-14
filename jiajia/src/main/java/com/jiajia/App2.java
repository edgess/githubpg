package com.jiajia;

import com.jiajia.entity.Oil;
import com.jiajia.server.OilService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App2 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        OilService oilService = ac.getBean(OilService.class);

        List<Oil> oils = oilService.getall();
        System.out.println(oils);

    }
}
