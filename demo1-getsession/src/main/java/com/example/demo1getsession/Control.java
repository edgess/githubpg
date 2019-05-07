package com.example.demo1getsession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class Control {
    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/getsession")
    public void getsession (HttpServletRequest request){
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttribute("key"));
    }
}
