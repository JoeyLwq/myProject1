package com.joey.user.dao;

import com.joey.user.pojo.User;
import dao.CommonDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends CommonDao<User> {
}
