package com.rootwish.bilu.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/11
 */
@Data
public class SmokeModel {
    //主键
    @TableId(value = "id")
    private Integer id;
    //烟名
    private String smokeName;
    //条数
    private String packOFNumber;
    //售价
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
