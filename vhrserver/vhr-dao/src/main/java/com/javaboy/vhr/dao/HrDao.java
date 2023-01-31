package com.javaboy.vhr.dao;

import com.javaboy.vhr.entity.Hr;
import com.javaboy.vhr.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Hr)表数据库访问层
 *
 * @author makejava
 * @since 2023-01-31 10:42:37
 */
public interface HrDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Hr queryById(Integer id);



    /**
     * 统计总行数
     *
     * @param hr 查询条件
     * @return 总行数
     */
    long count(Hr hr);

    /**
     * 新增数据
     *
     * @param hr 实例对象
     * @return 影响行数
     */
    int insert(Hr hr);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Hr> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Hr> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Hr> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Hr> entities);

    /**
     * 修改数据
     *
     * @param hr 实例对象
     * @return 影响行数
     */
    int update(Hr hr);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return Hr对象
     */
    Hr getHrByUsername(String username);

    /**
     * 根据用户id查询用户对应的所有角色
     * @param id 用户id
     * @return role集合
     */
    List< Role> getRolesByHrId(Integer id);
}

