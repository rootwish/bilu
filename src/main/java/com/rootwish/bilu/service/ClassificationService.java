package com.rootwish.bilu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rootwish.bilu.entity.ClassificationEntity;
import com.rootwish.bilu.model.ClassificationModel;

import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/5
 */
public interface ClassificationService extends IService<ClassificationEntity> {
    List<ClassificationModel> getParentCategory();
    ClassificationModel getClassification(String classiofyName);
    ClassificationModel getClassification(Integer id);
}
