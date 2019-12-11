package com.rootwish.bilu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rootwish.bilu.entity.InformationEntity;
import com.rootwish.bilu.entity.SmokeEntity;
import com.rootwish.bilu.mapper.InformationMapper;
import com.rootwish.bilu.mapper.SmokeMapper;
import com.rootwish.bilu.model.InformationModel;
import com.rootwish.bilu.service.InformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/10
 */
@Service
@Slf4j
public class InformationServiceImpl extends ServiceImpl<InformationMapper,InformationEntity> implements InformationService{
    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private SmokeMapper smokeMapper;

    @Override
    public boolean saveInformation(InformationModel informationModel) {
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.setSeized_time(informationModel.getSeized_time());
        informationEntity.setStart_time(informationModel.getStart_time());
        informationEntity.setSite(informationModel.getSite());
        informationEntity.setCertificateType(informationModel.getCertificateType());
        informationEntity.setCertificateNumber(informationModel.getCertificateNumber());
        informationEntity.setPhoneNumber(informationModel.getPhoneNumber());
        informationEntity.setAge(informationModel.getAge());
        informationEntity.setSex(informationModel.getSex());
        informationEntity.setTobaccoNumber(informationModel.getTobaccoNumber());
        informationEntity.setTheRealAddress(informationModel.getTheRealAddress());
        informationEntity.setBuckleSingleNumber(informationModel.getBuckleSingleNumber());
        informationEntity.setTheCaseNumber(informationModel.getTheCaseNumber());
        informationEntity.setPlaceOfDomicile(informationModel.getPlaceOfDomicile());
        informationEntity.setAdviceNoteValue(informationModel.getAdviceNoteValue());
        informationEntity.setClassificationId(informationModel.getClassificationId());
        boolean rel = true;
        int informationid = informationMapper.insert(informationEntity);
        if (0>=informationid){
            rel = false;
        }
        List<SmokeEntity> smoke = informationModel.getSmoke();
        for (int i =0;i<smoke.size();i++){
            SmokeEntity smokeEntity = smoke.get(i);
            smokeEntity.setInformationId(informationid);
            int smokeid = smokeMapper.insert(smokeEntity);
            if (0>=smokeid){
                rel = false;
            }
        }

        return rel;
    }

    @Override
    public List<InformationModel> getInformationList(Integer classificationId) {
        QueryWrapper<InformationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("classification_id",classificationId);
        List<InformationEntity> informationEntities = informationMapper.selectList(wrapper);
        List<InformationModel> informationModels = new ArrayList<>();
        for (int i = 0;i<informationEntities.size();i++){
            InformationModel informationModel = new InformationModel();
            informationModel.setId(informationEntities.get(i).getId());
            informationModel.setSeized_time(informationEntities.get(i).getSeized_time());
            informationModel.setStart_time(informationEntities.get(i).getStart_time());
            informationModel.setSite(informationEntities.get(i).getSite());
            informationModel.setCertificateType(informationEntities.get(i).getCertificateType());
            informationModel.setCertificateNumber(informationEntities.get(i).getCertificateNumber());
            informationModel.setPhoneNumber(informationEntities.get(i).getPhoneNumber());
            informationModel.setAge(informationEntities.get(i).getAge());
            informationModel.setSex(informationEntities.get(i).getSex());
            informationModel.setTobaccoNumber(informationEntities.get(i).getTobaccoNumber());
            informationModel.setTheRealAddress(informationEntities.get(i).getTheRealAddress());
            informationModel.setBuckleSingleNumber(informationEntities.get(i).getBuckleSingleNumber());
            informationModel.setTheCaseNumber(informationEntities.get(i).getTheCaseNumber());
            informationModel.setPlaceOfDomicile(informationEntities.get(i).getPlaceOfDomicile());
            informationModel.setAdviceNoteValue(informationEntities.get(i).getAdviceNoteValue());
            informationModel.setClassificationId(informationEntities.get(i).getClassificationId());
            QueryWrapper<SmokeEntity> smokeWrapper = new QueryWrapper<>();
            smokeWrapper.eq("information_id",informationEntities.get(i).getId());
            List<SmokeEntity> smokeEntities = smokeMapper.selectList(smokeWrapper);
            informationModel.setSmoke(smokeEntities);
            informationModels.add(informationModel);
        }
        return informationModels;
    }

    @Override
    public InformationModel getInformation(Integer id) {
        InformationEntity informationEntity = informationMapper.selectById(id);
        InformationModel informationModel = new InformationModel();
        informationModel.setId(informationEntity.getId());
        informationModel.setSeized_time(informationEntity.getSeized_time());
        informationModel.setStart_time(informationEntity.getStart_time());
        informationModel.setSite(informationEntity.getSite());
        informationModel.setCertificateType(informationEntity.getCertificateType());
        informationModel.setCertificateNumber(informationEntity.getCertificateNumber());
        informationModel.setPhoneNumber(informationEntity.getPhoneNumber());
        informationModel.setAge(informationEntity.getAge());
        informationModel.setSex(informationEntity.getSex());
        informationModel.setTobaccoNumber(informationEntity.getTobaccoNumber());
        informationModel.setTheRealAddress(informationEntity.getTheRealAddress());
        informationModel.setBuckleSingleNumber(informationEntity.getBuckleSingleNumber());
        informationModel.setTheCaseNumber(informationEntity.getTheCaseNumber());
        informationModel.setPlaceOfDomicile(informationEntity.getPlaceOfDomicile());
        informationModel.setAdviceNoteValue(informationEntity.getAdviceNoteValue());
        informationModel.setClassificationId(informationEntity.getClassificationId());
        QueryWrapper<SmokeEntity> smokeWrapper = new QueryWrapper<>();
        smokeWrapper.eq("information_id",informationEntity.getId());
        List<SmokeEntity> smokeEntities = smokeMapper.selectList(smokeWrapper);
        informationModel.setSmoke(smokeEntities);
        return informationModel;
    }

    @Override
    public void updateInformation(InformationModel informationModel) {
        InformationEntity informationEntity = new InformationEntity();
        informationEntity.setId(informationModel.getId());
        informationEntity.setSeized_time(informationModel.getSeized_time());
        informationEntity.setStart_time(informationModel.getStart_time());
        informationEntity.setSite(informationModel.getSite());
        informationEntity.setCertificateType(informationModel.getCertificateType());
        informationEntity.setCertificateNumber(informationModel.getCertificateNumber());
        informationEntity.setPhoneNumber(informationModel.getPhoneNumber());
        informationEntity.setAge(informationModel.getAge());
        informationEntity.setSex(informationModel.getSex());
        informationEntity.setTobaccoNumber(informationModel.getTobaccoNumber());
        informationEntity.setTheRealAddress(informationModel.getTheRealAddress());
        informationEntity.setBuckleSingleNumber(informationModel.getBuckleSingleNumber());
        informationEntity.setTheCaseNumber(informationModel.getTheCaseNumber());
        informationEntity.setPlaceOfDomicile(informationModel.getPlaceOfDomicile());
        informationEntity.setAdviceNoteValue(informationModel.getAdviceNoteValue());
        informationEntity.setClassificationId(informationModel.getClassificationId());
            int information = informationMapper.updateById(informationEntity);
            List<SmokeEntity> smoke = informationModel.getSmoke();
            for (int i = 0;i<smoke.size();i++ ){
                smokeMapper.updateById(smoke.get(i));
            }
    }

    @Override
    public void delInformation(InformationModel informationModel) {
        informationMapper.deleteById(informationModel.getId());
        List<SmokeEntity> smoke = informationModel.getSmoke();
        for (int i = 0;i<smoke.size();i++){
            smokeMapper.deleteById(smoke.get(i).getId());
        }
    }
}
