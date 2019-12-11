package com.rootwish.bilu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/10
 */
@Data
@TableName("smoke")
public class SmokeEntity {
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //烟名
    private String smokeName;
    //条数
    private String packOFNumber;
    //售价
    private String retailPrice;
    //基本信息id
    private Integer informationId;
}
