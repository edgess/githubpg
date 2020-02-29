package com.edge.daomy.mapper;

import com.edge.daomy.entity.Demousers;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Demousers)表数据库访问层
 *
 * @author edge
 * @since 2020-02-29 21:16:16
 */
public interface DemousersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Demousers queryById(Integer id);
    List<Demousers> getAll();
    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Demousers> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param demousers 实例对象
     * @return 对象列表
     */
    List<Demousers> queryAll(Demousers demousers);

    /**
     * 新增数据
     *
     * @param demousers 实例对象
     * @return 影响行数
     */
    int insert(Demousers demousers);

    /**
     * 修改数据
     *
     * @param demousers 实例对象
     * @return 影响行数
     */
    int update(Demousers demousers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}