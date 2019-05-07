package com.edge;

import com.edge.dao.server.UserService;
import com.edge.daojpa.TestRepository;
import com.edge.daomy.mapper.Agent;
import com.edge.dbutils.UserDAOJDBCImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    }

    @Test
    public void Test2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        System.out.println(ac);

    }
}
