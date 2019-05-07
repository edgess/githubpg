package com.example.demo1.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ControlSession {

    @RequestMapping(value = "/sessions")
    public Object sessions (String s,HttpServletRequest request,Map map){
        request.getSession().setAttribute("key",s);
        map.put("sessionId", request.getSession().getId());
        map.put("key",s);
        return "sessions";
    }
}
