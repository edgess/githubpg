package com.example.demomongo.mapper;


import com.example.demomongo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {

    @Select("select * from user ")
    List<User> findall();

    @Select("select r.rolename from user u, role r,userrole ur where u.username=#{username} and u.id=ur.userid and r.id=ur.roleid")
    Set<String> findRoles(String username);

    @Select("select p.permissionname from user u, role r, permission p, userrole ur, rolepermission rp where u.username=#{username} and u.id=ur.userid and r.id=ur.roleid and rp.roleid=r.id and rp.permissionid=p.id")
    Set<String> findPermission(String username);

    @Select("select * from user where username=#{username}")
    User findbyusername(String username);

    @Insert("insert into user (username,password,locked,expireddate) values (#{username},#{password},#{locked},#{expireddate})")
    int insert(User user);
}
