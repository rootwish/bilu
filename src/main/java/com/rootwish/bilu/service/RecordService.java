package com.rootwish.bilu.service;

import com.rootwish.bilu.model.RecordModel;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/13
 */
public interface RecordService {
    boolean saveRecord(RecordModel recordModel);
    RecordModel getRecord(Integer informationId);
    boolean delRecord(Integer informationId);
    boolean UpdateRecord(RecordModel recordModel);
}
