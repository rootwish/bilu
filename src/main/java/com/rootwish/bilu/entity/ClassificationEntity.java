package com.rootwish.bilu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/5
 */
@Data
@TableName("classification")
public class ClassificationEntity implements Serializable{

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String classifyName;

    private Integer pid;
}
