package com.joey.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/*
* 安全配置类
* */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        /*authorizeRequests表示开始说明需要的权限
        * antMatchers表示拦截什么路径，permitAll表示任何权限都能访问
        * anyRequest().authenticated()表示任何请求都需认证
        * and().csrf().disable()表示使csrf拦截失效*/
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
