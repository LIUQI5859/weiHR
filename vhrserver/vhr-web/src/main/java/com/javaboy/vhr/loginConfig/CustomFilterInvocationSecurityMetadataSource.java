package com.javaboy.vhr.loginConfig;

import com.javaboy.vhr.entity.Menu;
import com.javaboy.vhr.entity.Role;
import com.javaboy.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    MenuService menuService;

    @Override
    public Collection< ConfigAttribute > getAttributes(Object object) throws IllegalArgumentException {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus = menuService.getAllMenusWithRoles();

        for(Menu menu : menus){
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){
                List< Role > roles = menu.getRoles();
                String[] rolesArr = new String[roles.size()];
                for(int i=0; i<roles.size(); i++){
                    rolesArr[i] = roles.get(i).getName();
                }

                return SecurityConfig.createList(rolesArr);
            }
        }

        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection< ConfigAttribute > getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class< ? > clazz) {
        return true;
    }
}
