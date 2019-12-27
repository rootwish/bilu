package com.rootwish.bilu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rootwish.bilu.entity.ClassificationEntity;
import com.rootwish.bilu.mapper.ClassificationMapper;
import com.rootwish.bilu.model.ClassificationModel;
import com.rootwish.bilu.service.ClassificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/5
 */
@Service
@Slf4j
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper,ClassificationEntity> implements ClassificationService{

    @Autowired
    private ClassificationMapper classificationMapper;

    /**
     * 查询分类
     * @return
     */
    @Override
    public List<ClassificationModel> getParentCategory() {

        QueryWrapper<ClassificationEntity> wrapper = new QueryWrapper<>();
        wrapper.isNull("pid");
        List<ClassificationEntity> classificationEntities = classificationMapper.selectList(wrapper);
        List<ClassificationModel> list = new ArrayList<>();
        for (int i = 0; i<classificationEntities.size();i++){
            ClassificationEntity classificationEntity = classificationEntities.get(i);
            ClassificationModel classificationModel = new ClassificationModel();
            classificationModel.setId(classificationEntity.getId());
            classificationModel.setClassifyName(classificationEntity.getClassifyName());
            classificationModel.setPid(classificationEntity.getPid());
            QueryWrapper<ClassificationEntity> subwrapper = new QueryWrapper<>();
            subwrapper.eq("pid",classificationEntity.getId());
            List<ClassificationEntity> classificationEntities1 = classificationMapper.selectList(subwrapper);
            if (classificationEntities1.size()!=0){
                classificationModel.setSub(classificationEntities1);
            }
            list.add(classificationModel);
        }

        return list;
    }

    @Override
    public ClassificationModel getClassification(String classiofyName) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("classify_name",classiofyName);
            ClassificationEntity one = this.getOne(queryWrapper);
            ClassificationModel classificationModel = new ClassificationModel();
            classificationModel.setId(one.getId());
            classificationModel.setClassifyName(one.getClassifyName());
            classificationModel.setPid(one.getPid());
            return classificationModel;
    }

}
