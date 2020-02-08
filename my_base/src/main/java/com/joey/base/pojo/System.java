package com.joey.base.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import entity.common.CommonEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
@Table(name = "system")
@ExcelTarget("system")
public class System extends CommonEntity<System> {

    @Id
    @Excel(name = "id", width = 30)
    @Pattern(regexp = "[0-9]{0,6}",message = "只能为小于7位的数字")
    private String id;
    @Excel(name = "系统英文名", width = 30)
    @NotBlank
    private String systemEng;
    @Excel(name = "系统中文名", width = 30)
    private String systemChn;
    @Excel(name = "更新日期",width = 30,format = "yyyy-MM-dd")
    private Date updateTime;
}
