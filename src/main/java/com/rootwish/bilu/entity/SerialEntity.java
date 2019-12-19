package com.rootwish.bilu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("Serial")
public class SerialEntity {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private Date buildTime;
}
