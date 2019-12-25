package com.joey.user.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    private String id;

    private String userName;

    private String roleTypeId;

}
