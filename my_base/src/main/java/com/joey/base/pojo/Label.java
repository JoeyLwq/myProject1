package com.joey.base.pojo;

import entity.common.CommonEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_label")
public class Label extends CommonEntity<Label> {

    @Id
    private String id;
    private String labelname;
    private String state;
    private Long count;
    private Long fans;
    private String recommend;
}
