package com.example.demo5;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo5ApplicationTests {
    @Autowired
    private OilService oilService;
    @Autowired
    private OilRepository oilRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void contextLoads() {
        System.out.println(oilRepository.findById(1));

    }

    @Test
    public void contextLoads2() {
        System.out.println(userRepository.findByNameLike("%ia%"));
        System.out.println(userRepository.findByNameLike("%ia%").size());

        System.out.println(userRepository.findByAgeLessThan(20));
        System.out.println(userRepository.findByNameLikeAndAgeGreaterThan("%ia%", 20));

        System.out.println(oilRepository.getall().size());


    }


}
