package com.example.tkm;


import com.example.tkm.daotkmapper.ProductDao;
import com.example.tkm.entity.Product;
import com.example.tkm.test.service.impl.OilServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Frank
 * \* Date: 8/4/2017
 * \* Time: 2:25 PM
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TkmApplication.class)
public class SimpleTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    OilServiceImpl oilService;

    @Test
    public void testSelect() throws Exception {
        List<Product> productList = productDao.getProducts();
        System.out.println(productList);
        //        assertEquals(true, productList.size() > 0);
    }
    @Test
    public void testSelect2() throws Exception {
        System.out.println(oilService.queryAllByLimit(1, 1));
    }

}