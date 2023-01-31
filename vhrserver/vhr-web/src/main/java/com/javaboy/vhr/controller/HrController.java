package com.javaboy.vhr.controller;

import com.javaboy.vhr.entity.Hr;
import com.javaboy.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Hr)表控制层
 *
 * @author makejava
 * @since 2023-01-31 10:51:57
 */
@RestController
@RequestMapping("/hr")
public class HrController {
    /**
     * 服务对象
     */
    @Autowired
    HrService hrService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity< Hr > queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.hrService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param hr 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity< Hr > add(Hr hr) {
        return ResponseEntity.ok(this.hrService.insert(hr));
    }

    /**
     * 编辑数据
     *
     * @param hr 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity< Hr > edit(Hr hr) {
        return ResponseEntity.ok(this.hrService.update(hr));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity< Boolean > deleteById(Integer id) {
        return ResponseEntity.ok(this.hrService.deleteById(id));
    }

}

