package com.example.and.service;

import com.example.and.entity.User;
import com.example.and.mapper.UserMapper;
import com.example.and.token.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class LoginService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    public Map<String, String> login(String username, String password) {
        Map<String, String> resultmap = new HashMap<>();
        //错误输入
        if (null == username || null == password) {
            resultmap.put("status", "error");
            resultmap.put("message", "错误输入");
            System.out.println("null");
            return resultmap;
        }
        User user = userMapper.findbyusername(username);
        //无此用户
        if (user == null) {
            resultmap.put("status", "error");
            resultmap.put("message", "无此用户");
            return resultmap;
        }
        //密码错误
        if (!PasswordUtil.verify(password, user.getPassword())) {
            resultmap.put("status", "error");
            resultmap.put("message", "密码错误");
            return resultmap;
        }
        //锁定判断
        if (user.getLocked() == true) {
            resultmap.put("status", "error");
            resultmap.put("message", "用户锁定");
            return resultmap;
        }
        //过期判断
        if (null != user.getExpireddate() && user.getExpireddate().compareTo(new Date()) != 1) {
            resultmap.put("status", "error");
            resultmap.put("message", "用户过期");
            return resultmap;
        }
        //创建payload
        Set<String> roles = userMapper.findRoles(username);
        Set<String> permissions = userMapper.findPermissionUrlbyUsername(username);
        Map<String,Object> claims =new HashMap<>();
        claims.put("username",username);
        claims.put("role",roles);
        claims.put("permission",permissions);
        String token = jwtUtils.createJWT("jwt", claims);
        resultmap.put("status", "success");
        resultmap.put("message", token);
        return resultmap;
    }
}
