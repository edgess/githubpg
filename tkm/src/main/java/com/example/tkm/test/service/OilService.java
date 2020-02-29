package com.example.tkm.test.service;

import com.example.tkm.test.entity.Oil;
import java.util.List;

/**
 * (Oil)表服务接口
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
public interface OilService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Oil queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Oil> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param oil 实例对象
     * @return 实例对象
     */
    Oil insert(Oil oil);

    /**
     * 修改数据
     *
     * @param oil 实例对象
     * @return 实例对象
     */
    Oil update(Oil oil);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}