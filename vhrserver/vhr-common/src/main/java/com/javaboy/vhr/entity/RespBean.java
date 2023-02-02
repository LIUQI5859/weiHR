package com.javaboy.vhr.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class RespBean {

    private Integer status;
    private String message;
    private Object object;

    public RespBean() {
    }

    public RespBean(Integer status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }



    public static RespBean ok(String message){
        return new RespBean(200,message,null);
    }

    public static RespBean ok(String message, Object object){
        return new RespBean(200,message,object);
    }

    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    public static RespBean error(String message, Object object){
        return new RespBean(500,message,object);
    }
}
