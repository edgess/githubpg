package com.example.demo1;

import com.example.demo1.entity.Oil;
import com.example.demo1.services.OilService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1OilTests {
    @Autowired
    OilService oilService;

    @Test
    //insertone return new id
    public void contextLoads4() {
        Oil oil = new Oil();
        oil.setId(0);
        oil.setMile(0);
        oil.setCash(0);
        oil.setPrice(0.0D);
        oil.setDate(new Date());
        oilService.insertOil(oil);
        System.out.println(oil.getId());
    }

    @Test
    //insertone return new id
    public void contextLoads1() {
        Oil oil = new Oil();
        oil.setMile(1);
        oil.setCash(1);
        oil.setPrice(1);
        oil.setDate(new Date());
        oilService.insertOil(oil);
        System.out.println(oil.getId());
    }

    @Test
    //insertmany
    public void contextLoads2() {
        List<Oil> oils = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            Oil oil = new Oil();
            oil.setMile(i);
            oil.setCash(i);
            oil.setPrice(i);
            oil.setDate(new Date());
            oils.add(oil);
        }
        oilService.insertOils(oils);
    }

    @Test
    //querybyid
    public void contextLoads3() {
        System.out.println(oilService.queryById(101));
    }

    @Test
    //querybyid
    public void contextLoads5() {
        System.out.println(oilService.getall());
    }


}
