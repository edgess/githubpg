package com.example.tkm.test.service.impl;

import com.example.tkm.test.entity.Lab;
import com.example.tkm.test.dao.LabDao;
import com.example.tkm.test.service.LabService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Lab)表服务实现类
 *
 * @author makejava
 * @since 2020-02-27 13:01:46
 */
@Service("labService")
public class LabServiceImpl implements LabService {
    @Resource
    private LabDao labDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Lab queryById(Integer id) {
        return this.labDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Lab> queryAllByLimit(int offset, int limit) {
        return this.labDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param lab 实例对象
     * @return 实例对象
     */
    @Override
    public Lab insert(Lab lab) {
        this.labDao.insert(lab);
        return lab;
    }

    /**
     * 修改数据
     *
     * @param lab 实例对象
     * @return 实例对象
     */
    @Override
    public Lab update(Lab lab) {
        this.labDao.update(lab);
        return this.queryById(lab.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.labDao.deleteById(id) > 0;
    }
}