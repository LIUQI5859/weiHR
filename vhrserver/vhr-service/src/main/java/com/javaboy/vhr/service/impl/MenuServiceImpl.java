package com.javaboy.vhr.service.impl;

import com.javaboy.vhr.entity.Menu;
import com.javaboy.vhr.dao.MenuDao;
import com.javaboy.vhr.service.MenuService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * (Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-01-31 10:49:36
 */
@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    MenuDao menuDao;

    @Autowired
    RedisTemplate redisTemplate;



    @Override
    public List< Menu > getAllMenusWithRoles() {

        List< Menu > menusMysql = menuDao.getAllMenusWithRoles();
        return menusMysql;


//        BoundListOperations menusWithRoles = redisTemplate.boundListOps("menusWithRoles");
//        List<Menu> menusRedis = menusWithRoles.range(0, menusWithRoles.size());
//        if (menusRedis == null || menusRedis.isEmpty()) {
//            List< Menu > menusMysql = menuDao.getAllMenusWithRoles();
//            menusWithRoles.leftPush(menusMysql);
//           menusWithRoles.expire(2, TimeUnit.MINUTES);
//            return menusMysql;
//        }else{
//            return  menusRedis;
//        }


    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Menu queryById(Integer id) {
        return this.menuDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu insert(Menu menu) {
        this.menuDao.insert(menu);
        return menu;
    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu update(Menu menu) {
        this.menuDao.update(menu);
        return this.queryById(menu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.menuDao.deleteById(id) > 0;
    }
}
