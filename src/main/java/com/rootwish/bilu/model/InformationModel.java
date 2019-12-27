package com.rootwish.bilu.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rootwish.bilu.entity.SmokeEntity;
import lombok.Data;

import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/9
 */
@Data
public class InformationModel {
    //主键
    private Integer id;
    //查获时间
    private String seizedTime;
    //笔录开始时间
    private String startTime;
    //询问地点（手工输入）
    private String site;
    //证件类型(身份证 驾驶证 暂住证 临时身份证)
    private String certificateType;
    //证件编号
    private String certificateNumber;
    //电话号码
    private String phoneNumber;
    //年龄
    private String age;
    //性别
    private String sex;
    //户籍住址
    private String householdAddress;
    //实际住址
    private String theRealAddress;
    //扣单编号
    private String buckleSingleNumber;
    //案件编号
    private String theCaseNumber;
    //烟草信息
    private List<SmokeEntity> smoke;
    //证据处理通知书   乐烟存处[2018]第73-1号   乐
    private String le;
    //证据处理通知书   乐烟存处[2018]第73-1号   2018
    private String year;
    //分类id
    private Integer classificationId;
    //烟草证号
    private String tobaccoNumber;
    //姓名
    private String name;
    //模板
    private String record;
    //笔录
    private String note;
}
