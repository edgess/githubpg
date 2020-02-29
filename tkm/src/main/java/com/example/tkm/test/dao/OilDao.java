package com.example.tkm.test.dao;

import com.example.tkm.test.entity.Oil;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Oil)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
public interface OilDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Oil queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Oil> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param oil 实例对象
     * @return 对象列表
     */
    List<Oil> queryAll(Oil oil);

    /**
     * 新增数据
     *
     * @param oil 实例对象
     * @return 影响行数
     */
    int insert(Oil oil);

    /**
     * 修改数据
     *
     * @param oil 实例对象
     * @return 影响行数
     */
    int update(Oil oil);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}