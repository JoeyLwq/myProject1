package com.joey.user.controller;

import com.joey.user.pojo.User;
import com.joey.user.service.UserService;
import controller.CommonController;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends CommonController<User> {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/sendSms/{phone}")
    public Result sendSms(@PathVariable String phone){
        if (StringUtils.isEmpty(phone)){
            return new Result(false,StatusCode.ERROR,"电话不能为空");
        }
        return userService.sendSms(phone);
    }

    @PostMapping("/regist")
    public Result regist(@RequestBody UserController.RegistBody registBody) {
        User user = registBody.getUser();
        String checkCode = registBody.getCheckCode();
        if (StringUtils.isEmpty(user.getUserName())) {
            return new Result(false, StatusCode.ERROR, "请输入用户名");
        }
        if (StringUtils.isNotEmpty(user.getId())) {
            return new Result(false, StatusCode.ERROR, "id由系统自动生成");
        }
        String o = (String) redisTemplate.opsForValue().get(user.getPhone());
        if (StringUtils.isEmpty(o)){
            return new Result(false,StatusCode.ERROR,"请先获取并输入验证码信息");
        }
        if (o.equals(checkCode)){
            return userService.regist(user);
        }else {
            return new Result(false,StatusCode.ERROR,"验证码错误");
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getPassword())) {
            return new Result(false, StatusCode.ERROR, "请输入密码");
        }
        User userLogin = userService.login(user);
        if (userLogin == null) {
            return new Result(false, StatusCode.ERROR, "登录失败,请确认用户名及密码是否正确");
        }
        //生成token
        String tokenCreated = jwtUtil.createToken(userLogin.getId(), userLogin.getUserName(), userLogin.getPhone(), userLogin.getRoleTypeId());
        return new Result(true, StatusCode.OK, "登录成功", tokenCreated);
    }

    @Data
    private static class RegistBody{
        private User user;
        private String checkCode;
    }
}
