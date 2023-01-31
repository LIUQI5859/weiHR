package com.javaboy.vhr.service;

import com.javaboy.vhr.entity.Hr;


/**
 * (Hr)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 10:49:36
 */
public interface HrService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Hr queryById(Integer id);


    /**
     * 新增数据
     *
     * @param hr 实例对象
     * @return 实例对象
     */
    Hr insert(Hr hr);

    /**
     * 修改数据
     *
     * @param hr 实例对象
     * @return 实例对象
     */
    Hr update(Hr hr);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
