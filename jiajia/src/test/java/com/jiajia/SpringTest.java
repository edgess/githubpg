package com.jiajia;

import com.jiajia.entity.Oil;
import com.jiajia.server.OilService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest {
    @Autowired
    OilService oilService;

    @Test
    public void test1() {
        Oil oil =new Oil();
        oil.setPrice(444);
        oil.setMile(4444);
        oil.setDate(new Date());
        oil.setCash(44);
        oilService.insertOil(oil);
    }
}
