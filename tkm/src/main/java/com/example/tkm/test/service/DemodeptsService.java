package com.example.tkm.test.service;

import com.example.tkm.test.entity.Demodepts;
import java.util.List;

/**
 * (Demodepts)表服务接口
 *
 * @author edge
 * @since 2020-02-29 19:13:21
 */
public interface DemodeptsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Demodepts queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Demodepts> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param demodepts 实例对象
     * @return 实例对象
     */
    Demodepts insert(Demodepts demodepts);

    /**
     * 修改数据
     *
     * @param demodepts 实例对象
     * @return 实例对象
     */
    Demodepts update(Demodepts demodepts);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}