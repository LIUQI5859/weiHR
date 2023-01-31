package com.javaboy.vhr.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.extern.slf4j.Slf4j;
/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2023-01-31 10:13:20
 */
@Data
@Slf4j
public class Role implements Serializable {
    private static final long serialVersionUID = 171114808480380603L;
             private Integer id;
             private String name;
    /**
     * 角色名称
     */         private String namezh;


}

