package com.rootwish.bilu.service;

import com.rootwish.bilu.model.InformationModel;

import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/10
 */
public interface InformationService {
    boolean saveInformation(InformationModel informationModel);

    List<InformationModel> getInformationList(Integer classificationId);

    InformationModel getInformation(Integer id);

    void updateInformation(InformationModel informationModel);

    void delInformation(InformationModel informationModel);
    void getInformation();
    InformationModel getInformationFoName(String name);
}
