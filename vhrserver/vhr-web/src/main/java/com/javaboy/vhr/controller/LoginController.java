package com.javaboy.vhr.controller;

import com.javaboy.vhr.entity.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public RespBean login() {
       return RespBean.error("离线状态，请您登录");
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
