package com.edge;

import com.edge.dao.server.UserService;
import com.edge.daojpa.TestRepository;
import com.edge.daomy.mapper.Agent;
import com.edge.dbutils.UserDAOJDBCImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringTest {
    @Autowired
    TestRepository testRepository;
    @Autowired
    UserService userService;
    @Autowired
    Agent agent;

    @Test
    public void test1() {
//---------
        System.out.println(testRepository.findAll());
//---------
        System.out.println(userService.selectByPrimaryKey(1));
//---------
        System.out.println(agent.getDeptName());
//---------
        UserDAOJDBCImpl userDAOJDBC = new UserDAOJDBCImpl();
        System.out.println(userDAOJDBC.aa());
    }
}
