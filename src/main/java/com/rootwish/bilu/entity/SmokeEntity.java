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
    //年
    //主键
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    //烟名
    private String smokeName;
    //条数
    private String packOFNumber;
    //售价(条)
    private String retailPrice;
    //基本信息id
    private Integer informationId;
    //合计价格
    private String numberTotalPrice;
    //包装形式
    private String packageStyle;
    //样品基数（条）
    private String sampleBase;
    //抽样数量
    private String amplingNumber;
    //条包条形码
    private String tiaoBaoBarCode;
}
