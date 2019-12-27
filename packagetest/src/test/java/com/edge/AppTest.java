package com.edge;

import com.edge.dao.server.Log2Service;
import com.edge.dao.server.Name2FastDFSService;
import com.edge.dao.server.UserService;
import com.edge.dao.server.UtestService;
import com.edge.daojpa.TestRepository;
import com.edge.daomy.mapper.Agent;
import com.edge.dbutils.UserDAOJDBCImpl;
import com.edge.entity.Log2;
import com.edge.entity.Name2FastDFS;
import com.edge.entity.Utest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.UUID;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    @Test
    public void Test1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//---------
        TestRepository testRepository = ac.getBean(TestRepository.class);
        System.out.println(testRepository.findAll());
//---------
        UserService userService = (UserService) ac.getBean(UserService.class);
        System.out.println(userService.selectByPrimaryKey(1));
//---------
        Agent agent = (Agent) ac.getBean(Agent.class);
        System.out.println(agent.getDeptName());
//---------
        UserDAOJDBCImpl userDAOJDBC = new UserDAOJDBCImpl();
        System.out.println(userDAOJDBC.aa());
//---------
        Log2Service log2Service = (Log2Service) ac.getBean(Log2Service.class);
        System.out.println(log2Service.getall());

    }

    @Test
    public void Test2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println(ac);
    }

    @Test
    public void Test3() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UtestService utestService = (UtestService) ac.getBean(UtestService.class);
        System.out.println(utestService.getall());
    }

    @Test
    public void Test4() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UtestService utestService = (UtestService) ac.getBean(UtestService.class);
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Utest utest = new Utest();
            utest.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            utest.setCreatetime(new Date());
            utestService.insertUtest(utest);
        }
        System.out.println(System.currentTimeMillis() - t1);
    }
}
