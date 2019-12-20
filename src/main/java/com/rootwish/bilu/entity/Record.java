package com.rootwish.bilu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import sun.dc.pr.PRError;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/20
 */
@Data
@TableName(value = "record")
public class Record {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Integer informationId;

    private String record;

    private String classificationId;
}
