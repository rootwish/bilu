package com.rootwish.bilu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rootwish.bilu.entity.RecordEntity;
import com.rootwish.bilu.model.RecordModel;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/13
 */
public interface RecordService extends IService<RecordEntity> {
    boolean saveRecord(RecordModel recordModel);
    RecordModel getRecordFoInformationId(Integer informationId);
    RecordModel getRecordFoClassificationID(Integer classificationId);
    boolean delRecordFoinformationId(Integer informationId);
    boolean delRecordFoClassification(Integer classificationId);
    boolean UpdataRecord(RecordModel recordModel);
}
