package com.javaboy.vhr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = "com.javaboy.vhr.dao")
@EnableCaching
public class VhrWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(VhrWebApplication.class, args);
    }

}
