package com.example.tkm.test.service.impl;

import com.example.tkm.test.entity.Oil;
import com.example.tkm.test.dao.OilDao;
import com.example.tkm.test.service.OilService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Oil)表服务实现类
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
@Service("oilService")
public class OilServiceImpl implements OilService {
    @Resource
    private OilDao oilDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Oil queryById(Integer id) {
        return this.oilDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Oil> queryAllByLimit(int offset, int limit) {
        return this.oilDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param oil 实例对象
     * @return 实例对象
     */
    @Override
    public Oil insert(Oil oil) {
        this.oilDao.insert(oil);
        return oil;
    }

    /**
     * 修改数据
     *
     * @param oil 实例对象
     * @return 实例对象
     */
    @Override
    public Oil update(Oil oil) {
        this.oilDao.update(oil);
        return this.queryById(oil.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.oilDao.deleteById(id) > 0;
    }
}