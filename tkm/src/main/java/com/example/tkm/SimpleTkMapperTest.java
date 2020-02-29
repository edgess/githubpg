package com.example.tkm;

import com.example.tkm.daotkmapper.CategoryDao;
import com.example.tkm.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Frank
 * \* Date: 8/4/2017
 * \* Time: 2:32 PM
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TkmApplication.class)
public class SimpleTkMapperTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void selectAllTest() {
        List<Category> categories = categoryDao.selectAll();
        System.out.println(categories);
//        assertEquals(true, categories.size() > 0);
    }

    @Test
    public void insertTest() {
        Category newCategory = new Category();
        newCategory.setCategoryID(1000);
        newCategory.setCategoryName("test");
        newCategory.setDescription("for test");
        int result = categoryDao.insert(newCategory);
        assertEquals(1, result);
    }

    @Test
    public void deleteByExampleTest() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryID", 1);
        int result = categoryDao.deleteByExample(example);
        assertEquals(1, result);
    }

    @Test
    public void selectByExampleTest() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryID", 1);
//        criteria.orEqualTo("categoryID", 2);
        System.out.println(categoryDao.selectByExample(example));
    }
}