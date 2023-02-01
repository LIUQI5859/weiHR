package com.javaboy.vhr;

import com.javaboy.vhr.dao.MenuDao;
import com.javaboy.vhr.entity.Menu;
import com.javaboy.vhr.entity.RespBean;
import com.javaboy.vhr.loginConfig.VerificationCode;
import com.javaboy.vhr.service.HrService;
import com.javaboy.vhr.service.MenuService;
import com.javaboy.vhr.service.impl.HrServiceImpl;
import com.javaboy.vhr.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VhrWebApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {

        BoundListOperations menusWithRoles = redisTemplate.boundListOps("menusWithRoles");
        List<Menu> menusRedis = menusWithRoles.range(0, menusWithRoles.size());
        System.out.println(menusRedis);
    }

    @Test
    public void test02(){
        List<RespBean> respBeans = new ArrayList<>();
        RespBean respBean1 = new RespBean();
        RespBean respBean2 = new RespBean();
        RespBean respBean3 = new RespBean();

        respBean1.setMessage("1");
        respBean1.setStatus(1);
        respBean1.setObject(1);

        respBean2.setMessage("2");
        respBean2.setStatus(2);
        respBean2.setObject(2);

        respBean3.setMessage("3");
        respBean3.setStatus(3);
        respBean3.setObject(3);

        respBeans.add(respBean1);
        respBeans.add(respBean2);
        respBeans.add(respBean3);

        System.out.println(respBeans);
    }

}
