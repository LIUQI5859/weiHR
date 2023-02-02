package com.javaboy.vhr.loginConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if ( !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        //获取生成的验证码
        //String verify_code = (String) request.getSession().getAttribute("verify_code");
        String verify_code="1234";

        //判断当前登录数据格式是否为json格式，如果是则按照自定义方式处理，如果不是则按照父类方式处理
        if(request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().contains(MediaType.APPLICATION_JSON_UTF8_VALUE)){
            Map< String, String > loginData = new HashMap<>();
            try {
                //将传输过来的json数据映射成map集合
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //检测验证码是否正确
                String code = loginData.get("code");
                checkCode(response,code,verify_code);
            }

            //获得用户名密码并验证
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }else{

            checkCode(response,request.getParameter("code"),verify_code);
            return super.attemptAuthentication(request, response);
        }
           }


    public void checkCode(HttpServletResponse response, String code, String verify_code) {
        if(code == null || verify_code == null || "".equals(code) || !code.toLowerCase().equals(verify_code.toLowerCase())){
            throw new AuthenticationServiceException("验证码不正确，请重新输入");
        }
    }
}
