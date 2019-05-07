package com.example.and.mapper;


import com.example.and.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import javax.lang.model.element.NestingKind;
import java.util.List;
import java.util.Set;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user ")
    List<User> findall();

    @Select("select r.rolename from user u, role r,userrole ur where u.username=#{username} and u.id=ur.userid and r.id=ur.roleid")
    Set<String> findRoles(String username);

    //@Select("select p.permissionname from user u, role r, permission p, userrole ur, rolepermission rp where u.username=#{username} and u.id=ur.userid and r.id=ur.roleid and rp.roleid=r.id and rp.permissionid=p.id")
    @Select("SELECT p.url\n" +
            "FROM permission p\n" +
            "WHERE p.id IN (SELECT rp.permissionid\n" +
            "               FROM rolepermission rp\n" +
            "               WHERE rp.roleid IN (SELECT ur.roleid\n" +
            "                                   FROM userrole ur\n" +
            "                                   WHERE ur.userid IN (SELECT u.id\n" +
            "                                                       FROM user u\n" +
            "                                                       WHERE u.username = #{username})));")
    Set<String> findPermissionUrlbyUsername(String username);

    @Select("SELECT url FROM permission")
    Set<String> findAllPremissionUrl();

    @Select("select * from user where username=#{username}")
    User findbyusername(String username);

    @Insert("insert into user (username,password,locked,expireddate) values (#{username},#{password},#{locked},#{expireddate})")
    int insert(User user);

    @Update("update user set password = #{password} where id = #{id}")
    int updatepasswordbyid(User user);

}
