package com.joey.user.service;


import com.joey.user.dao.UserDao;
import com.joey.user.pojo.User;
import dao.CommonDao;
import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import service.CommonService;
import utils.IdGenerator;
import utils.QueryMaker;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService extends CommonService<User> {

    //加密
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Result regist(User user) {
        List<User> userCheck = userDao.findAll(new QueryMaker<>(user)
                .eq("userName", user.getUserName())
                .getSpecification());
        if (userCheck != null && userCheck.size() > 0) {
            return new Result(false, StatusCode.ERROR, "用户名已存在");
        }
        user.setId(IdGenerator.generatorId());
        if (StringUtils.isEmpty(user.getPassword())){
            return new Result(false,StatusCode.ERROR,"请输入密码");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        redisTemplate.delete(user.getPhone());
        return new Result(true, StatusCode.OK, "注册成功");
    }

    public User login(User user) {
        QueryMaker<User> queryMaker = new QueryMaker<>(user).eq("userName", user.getUserName());
        List<User> userGetFromDB = userDao.findAll(queryMaker.getSpecification());
        if (userGetFromDB != null) {
            if (encoder.matches(user.getPassword(), userGetFromDB.get(0).getPassword())) {
                return userGetFromDB.get(0);
            }
        }
        return null;
    }

    public Result sendSms(String phone) {
        //生成验证码
        String checkCode = IdGenerator.generatorId();
        //往缓存中放一份
        redisTemplate.opsForValue().set(phone, checkCode);
        //将电话和验证码封装进map
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("checkCode", checkCode);
        //给队列里放一份
        StringBuilder phoneSB = new StringBuilder(phone);
        String phoneNew = phoneSB.insert(3, ".").toString();
        rabbitTemplate.convertAndSend("smsChange",phoneNew,map);
        return new Result(true, StatusCode.OK, "发送成功");
    }
}
