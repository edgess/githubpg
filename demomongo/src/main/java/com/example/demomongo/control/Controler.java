package com.example.demomongo.control;

import com.alibaba.fastjson.JSONArray;
import com.example.demomongo.mango.MongoAutoidUtil;
import com.example.demomongo.mapper.QrcodeMapper;
import com.example.demomongo.mapper.UserMapper;
import com.example.demomongo.repository.LabRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Controler {
    @Autowired
    LabRepository labRepository;

    @Autowired
    MongoAutoidUtil mongoAutoidUtil;

    @Autowired
    QrcodeMapper qrcodeMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("test")
    public ModelAndView test() {
        if (null != httpServletRequest.getHeader("x-requested-with") && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //ajax请求时
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject("result", "failed");
//            mav.addObject("list", qrcodeMapper.findall());
            return mav;
        } else {
            //非ajax请求时
            ModelAndView mav = new ModelAndView("test");
            return mav;
        }


    }

    @RequestMapping("loginaaa")
    public String loginaaa() {
        System.out.println("loginaaa");
        return "loginaaa";
    }

    @RequestMapping("login")
    public ModelAndView login() {
        System.out.println(httpServletRequest.getHeader("x-requested-with"));
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @RequestMapping("dologin")
    public void dologin(String name, String pswd) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, pswd);
        subject.login(token);
        String returnstring = "success";
//        returnstring = TokenMgr.createJWT("jwt", username, 24 * 60 * 60 * 1000);
//        return returnstring;
    }

    @RequestMapping("getallqrcode")
    public String getallqrcode() {
        return JSONArray.toJSONString(qrcodeMapper.findall());
    }

    @RequiresRoles("admin")
    @RequestMapping("getlabfrommogo")
    public String getlabfrommogo() {
        return JSONArray.toJSONString(labRepository.findAll());
    }
}
