package com.rootwish.bilu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/13
 */
@Data
@TableName("record")
public class RecordEntity {
    //id
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //基本信息id
    private Integer informationId;
    //笔录内容
    private String record;
    //分类id
    private Integer classificationId;
}
