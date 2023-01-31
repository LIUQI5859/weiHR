package com.javaboy.vhr.service.impl;

import com.javaboy.vhr.entity.Hr;
import com.javaboy.vhr.dao.HrDao;
import com.javaboy.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * (Hr)表服务实现类
 *
 * @author makejava
 * @since 2023-01-31 10:49:36
 */
@Service("hrService")
public class HrServiceImpl implements HrService {
    @Autowired
    private HrDao hrDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Hr queryById(Integer id) {
        return this.hrDao.queryById(id);
    }



    /**
     * 新增数据
     *
     * @param hr 实例对象
     * @return 实例对象
     */
    @Override
    public Hr insert(Hr hr) {
        this.hrDao.insert(hr);
        return hr;
    }

    /**
     * 修改数据
     *
     * @param hr 实例对象
     * @return 实例对象
     */
    @Override
    public Hr update(Hr hr) {
        this.hrDao.update(hr);
        return this.queryById(hr.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.hrDao.deleteById(id) > 0;
    }
}
