package com.joey.user.intercepter;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtIntercepter implements HandlerInterceptor {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            throw new RuntimeException("请先登录并获取token");
        }
        final String token = authorization.substring(7);
        try {
            Claims claims = jwtUtil.parseToken(token);
            request.setAttribute("userInfo",claims);
        } catch (Exception e) {
            throw new RuntimeException("token已过期或有误");
        }
        return true;
    }
}
