package com.jiajia;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jiajia.entity.Oil;
import com.jiajia.server.OilService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
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

        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create(App.class.getClassLoader().getResource("ehcache.xml"));

        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");

        // 3. 创建元素
        Element element = new Element("key1", oils2);

        // 4. 将元素添加到缓存
        cache.put(element);

        // 5. 获取缓存
        Element value = cache.get("key1");
        System.out.println("ehcache=="+value);
        System.out.println("Gson ech----"+value.getObjectValue());

    }
}
