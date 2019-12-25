package entity.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class CommonEntity<T> implements Serializable {

    private String id;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private String isDelete;

}
