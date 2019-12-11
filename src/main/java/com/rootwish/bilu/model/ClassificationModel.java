package com.rootwish.bilu.model;

import com.rootwish.bilu.entity.ClassificationEntity;
import lombok.Data;
import sun.dc.pr.PRError;

import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/5
 */
@Data
public class ClassificationModel {

    private Integer id;

    private String classifyName;

    private Integer pid;

    private List<ClassificationEntity> sub;
}
