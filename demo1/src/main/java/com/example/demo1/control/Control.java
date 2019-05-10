package com.example.demo1.control;

import com.example.demo1.entity.UserExample;
import com.example.demo1.services.OilService;
import com.example.demo1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class Control {

    @Autowired
    OilService oilservice;
    @Autowired
    UserService userService;



    @RequestMapping(value = "/oil/getall",produces = "text/html; charset=UTF-8")
    public String getall(Map map) {
        map.put("oils", oilservice.getall());
        map.put("js", "<script>alert('请正确输入！');</script>");
        map.put("js2", "alert(123);");
        return "oil";
    }

    @RequestMapping("/oil/queryById")
    @ResponseBody
    public Object queryById(Integer i) {

        return oilservice.queryById(i);
    }

    @RequestMapping("/user/get")
    public String userget(Map map) {
        map.put("users", userService.selectByExample(new UserExample()));
        return "user";
    }


// @RequestMapping("/")
//    public String test(Model model) {
//        model.addAttribute("data", gMapper.getall());
//        return "test";
//    }
}
