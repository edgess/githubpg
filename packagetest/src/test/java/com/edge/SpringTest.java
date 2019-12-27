package com.edge;

import com.edge.dao.mapper.Log2Mapper;
import com.edge.dao.mapper.Name2FastDFSMapper;
import com.edge.dao.server.Log2Service;
import com.edge.dao.server.Name2FastDFSService;
import com.edge.dao.server.UserService;
import com.edge.daojpa.TestRepository;
import com.edge.daomy.mapper.Agent;
import com.edge.dbutils.UserDAOJDBCImpl;
import com.edge.entity.Name2FastDFS;
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
    TestRepository testRepository;
    @Autowired
    UserService userService;
    @Autowired
    Agent agent;
    @Autowired
    Name2FastDFSMapper name2FastDFSMapper;
    @Autowired
    Log2Mapper Log2Mapper;

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

    @Test
    public void test2() {

        Name2FastDFS name2FastDFS = new Name2FastDFS();
        name2FastDFS.setName("123");
////        name2FastDFS.setExt("jpg");
////        name2FastDFS.setFdfs("wKgKuF4EadaAQMWuAADkW_3yAvw317");
////        name2FastDFS.setDate(new Date());
        System.out.println(name2FastDFSMapper.findFdfsbyName(name2FastDFS));
    }
}
