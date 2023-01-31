package com.javaboy.vhr.service;

import com.javaboy.vhr.entity.Menu;

import java.util.List;


/**
 * (Menu)表服务接口
 *
 * @author makejava
 * @since 2023-01-31 10:49:36
 */
public interface MenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Menu queryById(Integer id);



    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 获取所有menu,其中封装着roles
     * @return
     */
    List< Menu> getAllMenusWithRoles();

}
