package com.example.and.control;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.and.entity.Qrcode;
import com.example.and.mapper.QrcodeMapper;
import com.example.and.service.LoginService;
import com.example.and.token.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class Control {

    @Autowired
    LoginService loginService;

    @Autowired
    QrcodeMapper qrcodeMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("getall")
    public String getall() {
        return JSONArray.toJSONString(qrcodeMapper.findall());
    }

    @RequestMapping("reccode")
    public String reccode(String para, String token) throws Exception {
        if (jwtUtils.validateJWT(token)) {

            Qrcode qrcode = new Qrcode();
            qrcode.setName((String) jwtUtils.parseJWT(token).get("username"));
            qrcode.setCode(para);
            qrcode.setDate(new Date());
            qrcode.setRemoteaddr(httpServletRequest.getRemoteAddr());
            qrcodeMapper.insert(qrcode);

            return "success";
        } else {
            return "error";
        }
    }

    @RequestMapping("login")
    public String login(String username, String password) {
        if (null != username && null != password) {
            if (username.equals("aaa") && password.equals("aaa")) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("username", username);
                String token = jwtUtils.createJWT("jwt", claims);
                return token;
            } else {
                return "loginerror";
            }
        } else {
            return "loginerror";
        }
    }

    @RequestMapping("logintoken")
    public String logintoken(String username, String password) {
        return JSONObject.toJSONString(loginService.login(username, password));
    }

    //    @Author(value = {"uuu", "user"}, logical = Logical.OR)
//    @Authornot(value = {"admin"})
    @RequestMapping("test1")
    public String test1(String wd, String token) {
        return "test1";
    }

    @RequestMapping("test2")
    public String test2(String wd, String token) {
        return "test2";
    }

    @RequestMapping("test3")
    public Object test3(String wd) {
        AJAXResult result = new AJAXResult();
        Map<String, String> claims = new HashMap<>();
        claims.put("eee", "jjj");
        claims.put("jjj", "打搭");
        List aa = new ArrayList();
        aa.add(claims);
        result.setData(aa);
        result.setSuccess(true);
        return result;
    }
}