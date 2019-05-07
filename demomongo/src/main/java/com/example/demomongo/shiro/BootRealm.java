package com.example.demomongo.shiro;


import com.example.demomongo.entity.User;
import com.example.demomongo.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class BootRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取前端输入的用户信息，封装为User对象
        User userweb = (User) principals.getPrimaryPrincipal();
        //获取前端输入的用户名
        String username = userweb.getUsername();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        获取角色、权限
        Set<String> roles = userMapper.findRoles(username);
        Set<String> permissions = userMapper.findPermission(username);
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
//        得到用户名
        String username = (String) token.getPrincipal();
//        得到密码
//        String password = new String((char[]) token.getCredentials());
        User user = userMapper.findbyusername(username);

        if (user == null) {
            return null;
        }
//锁定判断
        if (user.getLocked() == true) {
            throw new LockedAccountException();
        }
//过期判断
        if (null != user.getExpireddate() && user.getExpireddate().compareTo(new Date()) != 1) {
            throw new ExpiredCredentialsException();
        }


        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

}
