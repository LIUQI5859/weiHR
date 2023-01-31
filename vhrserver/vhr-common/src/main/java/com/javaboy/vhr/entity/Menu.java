package com.javaboy.vhr.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.extern.slf4j.Slf4j;
/**
 * (Menu)实体类
 *
 * @author makejava
 * @since 2023-01-31 10:13:20
 */
@Data
@Slf4j
public class Menu implements Serializable {
    private static final long serialVersionUID = 889199038254793736L;
             private Integer id;
             private String url;
             private String path;
             private String component;
             private String name;
             private String iconCls;
             private Integer keepAlive;
             private Integer requireAuth;
             private Integer parentId;
             private Integer enabled;
             private List<Role> roles;


}

