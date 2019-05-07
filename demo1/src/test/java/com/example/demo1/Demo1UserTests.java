package com.example.demo1;

import com.example.demo1.entity.User;
import com.example.demo1.entity.UserExample;
import com.example.demo1.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1UserTests {
    @Autowired
    UserService userService;

    @Test
    //queryById
    public void contextLoads1() {
        System.out.println(userService.selectByPrimaryKey(1));
    }

    @Test
    //insetone
    public void contextLoads2() {
        User user = new User();
        user.setName("xiaoyu");
        user.setAge(40);
        boolean ret = userService.insert(user) == 1;
        System.out.println(ret);
        System.out.println(user);
    }

    @Test
    public void example9() {
        User user = userService.selectByPrimaryKey(9);
        System.out.println(user);
        user.setName("小辉");
        System.out.println(userService.updateByPrimaryKeySelective(user));
    }

    @Test
    public void example8() {
        User user = new User();
        user.setName("李白");
        System.out.println(userService.insertSelective(user));
    }

    @Test
    public void example7() {
        System.out.println(userService.deleteByPrimaryKey(6));
    }

    @Test
    public void example6() {

//        LabExample example3 = new LabExample();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//        Date startDate = dateFormat.parse("20180910");
//        Date endDate = dateFormat.parse("20180912");
//        example3.createCriteria().andCreatetimeBetween(startDate, endDate);
//        System.out.println(labMapper.selectByExample(example3).size());
    }

    @Test
    public void example5() {
        // 相当于：select count(*) from user
        UserExample userExample = new UserExample();
        System.out.println(userService.selectByExample(userExample).size());

    }

    @Test
    public void example4() {
        // 相当于：select * from user where name like "%e%"
        UserExample userExample = new UserExample();
        userExample.createCriteria().andNameLike("%e%");
        System.out.println(userService.selectByExample(userExample));
    }

    @Test
    public void example3() {
        // 相当于：select * from user where name in ("rrr","qiqi")
        List<String> fieldVal = new ArrayList<>();
        fieldVal.add("rrr");
        fieldVal.add("qiqi");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameIn(fieldVal);
        System.out.println(userService.selectByExample(userExample));
    }

    @Test
    public void example2() {
        // 相当于：select * from user where age = 15 and name is not null order by age desc,name asc
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAgeEqualTo(15);
        criteria.andNameIsNotNull();
        userExample.setOrderByClause("age desc,name asc");
        System.out.println(userService.selectByExample(userExample));
    }

    @Test
    public void example1() {
        // 相当于：select * from user where age = 15
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAgeEqualTo(15);
        System.out.println(userService.selectByExample(userExample));
    }
}
