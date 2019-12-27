package com.rootwish.bilu.service;

import com.rootwish.bilu.entity.ClassificationEntity;
import com.rootwish.bilu.model.ClassificationModel;

import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/5
 */
public interface ClassificationService {
    List<ClassificationModel> getParentCategory();
    ClassificationModel getClassification(String classiofyName);
}
