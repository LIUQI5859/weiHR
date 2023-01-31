package com.javaboy.vhr.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.extern.slf4j.Slf4j;
/**
 * (Hr)实体类
 *
 * @author makejava
 * @since 2023-01-31 10:13:20
 */
@Data
@Slf4j
public class Hr implements Serializable {
    private static final long serialVersionUID = 964959191744692365L;
    /**
     * hrID
     */         private Integer id;
    /**
     * 姓名
     */         private String name;
    /**
     * 手机号码
     */         private String phone;
    /**
     * 住宅电话
     */         private String telephone;
    /**
     * 联系地址
     */         private String address;
             private Integer enabled;
    /**
     * 用户名
     */         private String username;
    /**
     * 密码
     */         private String password;
             private String userface;
             private String remark;


}

