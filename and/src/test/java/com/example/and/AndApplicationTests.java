package com.example.and;

import com.example.and.entity.User;
import com.example.and.mapper.UserMapper;
import com.example.and.service.PasswordUtil;
import com.example.and.token.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties(prefix = "loginignore")
public class AndApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtils jwtUtils;

    private List<String> ignoreURLs = new ArrayList<>();

    public void setIgnoreURLs(List<String> ignoreURLs) {
        this.ignoreURLs = ignoreURLs;
    }

    @Test
    public void contextLoads4() throws Exception {
        System.out.println(ignoreURLs);
    }

    @Test
    public void contextLoads3() throws Exception {
        System.out.println(userMapper.findAllPremissionUrl());
        System.out.println(userMapper.findPermissionUrlbyUsername("qiqi"));
    }
    @Test
    public void contextLoads2() throws Exception {
        //创建payload
        String username = "edge";
        Set<String> roles = userMapper.findRoles(username);
        Set<String> permissions = userMapper.findPermissionUrlbyUsername(username);
        Map<String,Object> claims =new HashMap<>();
        claims.put("username",username);
        claims.put("role",roles);
        claims.put("permission",permissions);

        String token = jwtUtils.createJWT("jwt", claims);
        System.out.println(token);
        System.out.println(jwtUtils.validateJWT(token));
        System.out.println(jwtUtils.parseJWT(token).get("role"));
        System.out.println(jwtUtils.parseJWT(token).get("permission"));
        System.out.println(jwtUtils.parseJWT(token).get("username"));
    }
    @Test
    public void contextLoads() {
        List<User> users = userMapper.findall();

        for (User user : users) {
            user.setPassword(PasswordUtil.generate("1234"));
            userMapper.updatepasswordbyid(user);
        }

    }
}

