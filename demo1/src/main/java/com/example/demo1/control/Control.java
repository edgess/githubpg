package com.example.demo1.control;

import com.example.demo1.entity.Oil;
import com.example.demo1.entity.UserExample;
import com.example.demo1.services.OilService;
import com.example.demo1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
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
        return "oil";
    }

    @RequestMapping(value = "/oil/getall2")
    @ResponseBody
    public Object getall2() {
        List<Oil> re1=oilservice.getall();
        List<Oil> re = new ArrayList<>();
        for (int i = 0; i <25 ; i++) {
            Oil re2=re1.get(i);
            re.add(re2);
        }
        return re;
    }

    @RequestMapping("/oil/queryById")
    @ResponseBody
    public Object queryById(Integer i) {

        return oilservice.queryById(i);
    }

    @RequestMapping("/oil/queryById1")
    @ResponseBody
    public Object queryById1() {
        return oilservice.queryById(379);
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
