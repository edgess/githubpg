package com.example.tkm.test.service.impl;

import com.example.tkm.test.entity.Demodepts;
import com.example.tkm.test.dao.DemodeptsDao;
import com.example.tkm.test.service.DemodeptsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Demodepts)表服务实现类
 *
 * @author edge
 * @since 2020-02-29 19:13:22
 */
@Service("demodeptsService")
public class DemodeptsServiceImpl implements DemodeptsService {
    @Resource
    private DemodeptsDao demodeptsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Demodepts queryById(Integer id) {
        return this.demodeptsDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Demodepts> queryAllByLimit(int offset, int limit) {
        return this.demodeptsDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param demodepts 实例对象
     * @return 实例对象
     */
    @Override
    public Demodepts insert(Demodepts demodepts) {
        this.demodeptsDao.insert(demodepts);
        return demodepts;
    }

    /**
     * 修改数据
     *
     * @param demodepts 实例对象
     * @return 实例对象
     */
    @Override
    public Demodepts update(Demodepts demodepts) {
        this.demodeptsDao.update(demodepts);
        return this.queryById(demodepts.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.demodeptsDao.deleteById(id) > 0;
    }
}