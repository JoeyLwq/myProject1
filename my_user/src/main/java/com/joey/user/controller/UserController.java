package com.joey.user.controller;

import com.joey.user.pojo.User;
import com.joey.user.service.UserService;
import controller.CommonController;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController extends CommonController<User> {
}
