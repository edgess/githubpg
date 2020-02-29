package com.edge;

import com.edge.dao.mapper.Log2Mapper;
import com.edge.dao.mapper.Name2FastDFSMapper;
import com.edge.dao.server.Log2Service;
import com.edge.dao.server.Name2FastDFSService;
import com.edge.dao.server.UserService;
import com.edge.daojpa.TestRepository;
import com.edge.daomy.entity.Demousers;
import com.edge.daomy.mapper.Agent;
import com.edge.daomy.mapper.DemodeptsDao;
import com.edge.daomy.mapper.DemousersDao;
import com.edge.daotkmapper.CategoryDao;
import com.edge.daotkmapper.ProductDao;
import com.edge.dbutils.UserDAOJDBCImpl;
import com.edge.entity.Category;
import com.edge.entity.Name2FastDFS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

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
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ProductDao  productDao;
    @Autowired
    DemodeptsDao demodeptsDao;
    @Autowired
    DemousersDao demousersDao;

    @Test
    public void test3() {
//        System.out.println(Log2Mapper.findall());
        System.out.println(categoryDao.selectAll());
        System.out.println(productDao.selectAll());
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryID", 4);
//        criteria.orEqualTo("categoryID", 2);
        System.out.println(categoryDao.selectByExample(example));

    }

    @Test
    public void test1() {
//---------
//        System.out.println(testRepository.findAll());
////---------
//        System.out.println(userService.selectByPrimaryKey(1));
////---------
        System.out.println(agent.getDeptName());
        System.out.println(demodeptsDao.getAll());
        System.out.println(demousersDao.getAll());

//---------
//        UserDAOJDBCImpl userDAOJDBC = new UserDAOJDBCImpl();
//        System.out.println(userDAOJDBC.aa());
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
