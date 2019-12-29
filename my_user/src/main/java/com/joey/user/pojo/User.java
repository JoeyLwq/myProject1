package com.joey.user.pojo;


import entity.common.CommonEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_user")
public class User extends CommonEntity<User> {

    @Id
    private String id;

    private String userName;

    private String roleTypeId;

    private String password;

    private String phone;

}
