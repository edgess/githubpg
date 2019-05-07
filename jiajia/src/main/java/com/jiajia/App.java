package com.jiajia;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jiajia.entity.Oil;
import com.jiajia.server.OilService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        OilService oilService = ac.getBean(OilService.class);

        List<Oil> oils = oilService.getall();
        System.out.println(oils);

        Gson gson = new Gson();
        String gstring = gson.toJson(oils);
        System.out.println("Gson string----" + gstring);

        List<Oil> oils2 = gson.fromJson(gstring, oils.getClass());
        System.out.println("Gson obj----" + oils2);

        JSONObject jsob = new JSONObject();
        String fstring = jsob.toJSONString(oils.get(0));
        System.out.println("fastjson string----" + fstring);

    }
}
