package com.example.demo9;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.EmptyWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo9.entity.Lab;
import com.example.demo9.entity.Oil;
import com.example.demo9.mapper.LabMapper;
import com.example.demo9.mapper.OilMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo9ApplicationTests {
    @Autowired
    OilMapper oilMapper;
    @Autowired
    LabMapper labMapper;

    @Test
    public void contextLoads() {
        oilMapper.selectList(new QueryWrapper<Oil>().eq("id", 330)).forEach(System.out::println);
    }

    @Test
    public void contextLoads1() {
        Oil oil =new Oil();
        oil.setCash(789);
        oilMapper.update(oil,new QueryWrapper<Oil>().eq("id",330));
    }

    @Test
    public void contextLoads2() {
        System.out.println(labMapper.getlastone());
        System.out.println(labMapper.selectList(new QueryWrapper<Lab>().eq("id", 500)));
    }
}

