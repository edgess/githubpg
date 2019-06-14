package com.example.fileserver;

import com.edge.dao.server.Log2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Model3ApplicationTests {
    @Autowired
    Log2Service log2Service;

    @Test
    public void contextLoads() {
        System.out.println(log2Service.getall());
    }

}
