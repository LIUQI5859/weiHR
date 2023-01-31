package com.javaboy.vhr.loginConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaboy.vhr.entity.Hr;
import com.javaboy.vhr.entity.RespBean;
import com.javaboy.vhr.service.impl.HrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    HrServiceImpl hrServiceImpl;

    @Autowired
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;

    @Autowired
    CustomAccessDecisionManager customAccessDecisionManager;

    @Bean
     PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor< FilterSecurityInterceptor >() {
                    @Override
                    public < O extends FilterSecurityInterceptor > O postProcess(O object) {
                        object.setAccessDecisionManager(customAccessDecisionManager);
                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);

                        return object;
                    }
                })
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        Hr hr = (Hr)auth.getPrincipal();
                        hr.setPassword(null);
                        RespBean ok = RespBean.ok("登录成功", hr);
                        PrintWriter out = resp.getWriter();
                        String s = new ObjectMapper().writeValueAsString(ok);
                        out.write(s);
                        out.flush();
                        out.close();
                    }
                }).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                RespBean error = RespBean.error(e.getMessage());
                if(e instanceof LockedException){
                    error.setMessage("账户被锁定，请联系管理员");
                }else if(e instanceof AccountExpiredException){
                    error.setMessage("账户已过期，请联系管理员");
                }else if(e instanceof CredentialsExpiredException){
                    error.setMessage("密码已过期，请联系管理员");
                }else if(e instanceof DisabledException){
                    error.setMessage("账户被禁用，请联系管理员");
                }else if(e instanceof BadCredentialsException){
                    error.setMessage("账号或密码错误，请重新输入");
                }

                String s = new ObjectMapper().writeValueAsString(error);
                out.write(s);
                out.flush();
                out.close();
            }
        }).permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    RespBean logoutOk = RespBean.ok("注销成功");
                    String s = new ObjectMapper().writeValueAsString(logoutOk);
                    out.write(s);
                    out.flush();
                    out.close();

                }).permitAll()
                .and()
                .csrf().disable();
    }
}
