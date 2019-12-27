package com.rootwish.bilu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rootwish.bilu.entity.RecordEntity;
import com.rootwish.bilu.mapper.RecordMapper;
import com.rootwish.bilu.model.RecordModel;
import com.rootwish.bilu.service.RecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/13
 */
@Service
@Slf4j
public class RecordServiceImpl extends ServiceImpl<RecordMapper,RecordEntity> implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public boolean saveRecord(RecordModel recordModel) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setInformationId(recordModel.getInformationId());
        recordEntity.setRecord(recordModel.getRecord());
        recordEntity.setClassificationId(recordModel.getClassificationId());
        int insert = recordMapper.insert(recordEntity);
        if (0<insert){
            return true;
        }

        return false;
    }


    @Override
    public RecordModel getRecordFoInformationId(Integer informationId) {
        QueryWrapper<RecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("information_id",informationId);
        RecordEntity recordEntity = recordMapper.selectOne(wrapper);
        RecordModel recordModel = new RecordModel();
        recordModel.setId(recordEntity.getId());
        recordModel.setInformationId(recordEntity.getInformationId());
        recordModel.setRecord(recordEntity.getRecord());
        recordModel.setClassificationId(recordEntity.getClassificationId());
        return recordModel;
    }

    @Override
    public RecordModel getRecordFoClassificationID(Integer classificationId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("classification_id",classificationId);
        RecordEntity one = this.getOne(queryWrapper);
        RecordModel recordModel = new RecordModel();
        recordModel.setId(one.getId());
        recordModel.setInformationId(one.getInformationId());
        recordModel.setRecord(one.getRecord());
        recordModel.setClassificationId(one.getClassificationId());
        return recordModel;
    }

    @Override
    public boolean delRecordFoinformationId(Integer informationId) {
        QueryWrapper<RecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("information_id",informationId);
        int delete = recordMapper.delete(wrapper);
        if (0<delete){
            return true;
        }
        return false;
    }

    @Override
    public boolean delRecordFoClassification(Integer classificationId) {
        QueryWrapper<RecordEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("classification_id",classificationId);
        int delete = recordMapper.delete(wrapper);
        if (0<delete){
            return true;
        }
        return false;
    }

    @Override
    public boolean UpdataRecord(RecordModel recordModel) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setId(recordModel.getId());
        recordEntity.setInformationId(recordModel.getInformationId());
        recordEntity.setRecord(recordModel.getRecord());
        recordEntity.setClassificationId(recordModel.getClassificationId());

        return this.updateById(recordEntity);
    }
}
