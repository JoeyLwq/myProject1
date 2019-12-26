package com.joey.user.service;


import com.joey.user.pojo.User;
import org.springframework.stereotype.Service;
import service.CommonService;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService extends CommonService<User> {
}
