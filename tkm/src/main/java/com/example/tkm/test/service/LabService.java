package com.example.tkm.test.service;

import com.example.tkm.test.entity.Lab;
import java.util.List;

/**
 * (Lab)表服务接口
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
public interface LabService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Lab queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Lab> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param lab 实例对象
     * @return 实例对象
     */
    Lab insert(Lab lab);

    /**
     * 修改数据
     *
     * @param lab 实例对象
     * @return 实例对象
     */
    Lab update(Lab lab);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}