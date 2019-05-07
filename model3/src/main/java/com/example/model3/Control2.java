package com.example.model3;

import com.edge.dao.server.OilService;
import com.edge.dao.server.UserService;
import com.edge.daojpa.TestRepository;
import com.edge.daomy.mapper.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Control2 {

    @Autowired
    UserService userService;

    @Autowired
    OilService oilService;

    @Autowired
    Agent agent;

    @Autowired
    TestRepository testRepository;

    @RequestMapping("/sp")
    @ResponseBody
    public Object sp() {
        return userService.selectByPrimaryKey(1);
    }

    @RequestMapping("/oil")
    @ResponseBody
    public Object oil() {
        return oilService.getall();
    }

    @RequestMapping("/dept")
    @ResponseBody
    public Object dept() {
        return agent.getDeptName();
    }
    
    @RequestMapping("/test")
    @ResponseBody
    public Object test() {
        return testRepository.findById(6);
    }
}
