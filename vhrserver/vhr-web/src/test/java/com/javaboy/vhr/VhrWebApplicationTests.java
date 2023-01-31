package com.javaboy.vhr;

import com.javaboy.vhr.dao.MenuDao;
import com.javaboy.vhr.entity.Menu;
import com.javaboy.vhr.service.HrService;
import com.javaboy.vhr.service.MenuService;
import com.javaboy.vhr.service.impl.HrServiceImpl;
import com.javaboy.vhr.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@SpringBootTest
class VhrWebApplicationTests {

    @Test
    void contextLoads() {
    }

}
