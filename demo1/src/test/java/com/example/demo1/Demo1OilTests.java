package com.example.demo1;

import com.example.demo1.entity.Log2;
import com.example.demo1.entity.Log2Example;
import com.example.demo1.mapper.Log2Mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1OilTests {
//    @Autowired
//    OilService oilService;

    @Autowired
    Log2Mapper log2Mapper;

//    @Test
//    //insertone return new id
//    public void contextLoads4() {
//        Oil oil = new Oil();
//        oil.setId(0);
//        oil.setMile(0);
//        oil.setCash(0);
//        oil.setPrice(0.0D);
//        oil.setDate(new Date());
//        oilService.insertOil(oil);
//        System.out.println(oil.getId());
//    }
//
//    @Test
//    //insertone return new id
//    public void contextLoads1() {
//        Oil oil = new Oil();
//        oil.setMile(1);
//        oil.setCash(1);
//        oil.setPrice(1);
//        oil.setDate(new Date());
//        oilService.insertOil(oil);
//        System.out.println(oil.getId());
//    }
//
//    @Test
//    //insertmany
//    public void contextLoads2() {
//        List<Oil> oils = new ArrayList<>();
//        for (int i = 1; i < 100; i++) {
//            Oil oil = new Oil();
//            oil.setMile(i);
//            oil.setCash(i);
//            oil.setPrice(i);
//            oil.setDate(new Date());
//            oils.add(oil);
//        }
//        oilService.insertOils(oils);
//    }

    @Test
    public void contextLoads5() {
//        System.out.println(oilService.queryById(101));
//        System.out.println(oilService.getall());


        Log2Example log2Example = new Log2Example();
        System.out.println(log2Mapper.countByExample(log2Example));

        Log2Example.Criteria criteria = log2Example.createCriteria();
        criteria.andCodeLike("123");
        System.out.println(log2Mapper.selectByExample(log2Example));


    }


}
