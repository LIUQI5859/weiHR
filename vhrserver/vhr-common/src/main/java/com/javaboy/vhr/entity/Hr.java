package com.javaboy.vhr.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * (Hr)实体类
 *
 * @author makejava
 * @since 2023-01-31 10:13:20
 */
@Data
@Slf4j
public class Hr implements Serializable,UserDetails {
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
                private Boolean enabled;
    /**
     * 用户名
     */         private String username;
    /**
     * 密码
     */         private String password;
                private String userface;
                private String remark;
                private List<Role> roles;

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities() {
        List< SimpleGrantedAuthority > authorities = new ArrayList<>(roles.size());
        for(Role role :roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

