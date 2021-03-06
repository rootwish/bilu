package com.rootwish.bilu.model;

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
public class RecordModel {
    //id
    private Integer id;
    //笔录内容
    private String record;
    //分类id
    private Integer classificationId;
}
