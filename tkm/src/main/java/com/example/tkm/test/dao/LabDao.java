package com.example.tkm.test.dao;

import com.example.tkm.test.entity.Lab;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Lab)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
public interface LabDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Lab queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Lab> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param lab 实例对象
     * @return 对象列表
     */
    List<Lab> queryAll(Lab lab);

    /**
     * 新增数据
     *
     * @param lab 实例对象
     * @return 影响行数
     */
    int insert(Lab lab);

    /**
     * 修改数据
     *
     * @param lab 实例对象
     * @return 影响行数
     */
    int update(Lab lab);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}