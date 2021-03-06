package com.example.tkm.test.dao;

import com.example.tkm.test.entity.Demodepts;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Demodepts)表数据库访问层
 *
 * @author edge
 * @since 2020-02-29 19:13:20
 */
public interface DemodeptsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Demodepts queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Demodepts> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param demodepts 实例对象
     * @return 对象列表
     */
    List<Demodepts> queryAll(Demodepts demodepts);

    /**
     * 新增数据
     *
     * @param demodepts 实例对象
     * @return 影响行数
     */
    int insert(Demodepts demodepts);

    /**
     * 修改数据
     *
     * @param demodepts 实例对象
     * @return 影响行数
     */
    int update(Demodepts demodepts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}