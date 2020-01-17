package com.joey.user.config;


import com.joey.user.intercepter.JwtIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;

@Configuration
public class IntercepterConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtIntercepter jwtIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtIntercepter)
                .addPathPatterns("/**")
                .excludePathPatterns(new ArrayList<String>(){{
                    add("/**/login/**");
                    add("/**/regist/**");
                    add("/**/sendSms/**");
                    add("/**/try*/**");
                }});
        super.addInterceptors(registry);
    }
}
