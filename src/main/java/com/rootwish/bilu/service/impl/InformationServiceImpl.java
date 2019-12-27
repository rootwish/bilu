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
        informationEntity.setSeizedTime(informationModel.getSeizedTime());
        informationEntity.setStartTime(informationModel.getStartTime());
        informationEntity.setSite(informationModel.getSite());
        informationEntity.setCertificateType(informationModel.getCertificateType());
        informationEntity.setCertificateNumber(informationModel.getCertificateNumber());
        informationEntity.setPhoneNumber(informationModel.getPhoneNumber());
        informationEntity.setAge(informationModel.getAge());
        informationEntity.setSex(informationModel.getSex());
        informationEntity.setHouseholdAddress(informationModel.getHouseholdAddress());
        informationEntity.setTobaccoNumber(informationModel.getTobaccoNumber());
        informationEntity.setTheRealAddress(informationModel.getTheRealAddress());
        informationEntity.setBuckleSingleNumber(informationModel.getBuckleSingleNumber());
        informationEntity.setTheCaseNumber(informationModel.getTheCaseNumber());
        informationEntity.setLe(informationModel.getLe());
        informationEntity.setYear(informationModel.getYear());
        informationEntity.setClassificationId(informationModel.getClassificationId());
        informationEntity.setName(informationModel.getName());
        boolean rel = true;
        int informationid = informationMapper.insert(informationEntity);
        if (0>=informationid){
            rel = false;
        }
        List<SmokeEntity> smoke = informationModel.getSmoke();
        for (int i =0;i<smoke.size();i++){
            SmokeEntity smokeEntity = smoke.get(i);
            smokeEntity.setInformationId(informationEntity.getId());
            String packOFNumber = smokeEntity.getPackOfNumber();
            String retailPrice = smokeEntity.getRetailPrice();
            int numberTotalPrice = Integer.parseInt(packOFNumber)+Integer.parseInt(retailPrice);
            smokeEntity.setNumberTotalPrice(numberTotalPrice+"");
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
            informationModel.setSeizedTime(informationEntities.get(i).getSeizedTime());
            informationModel.setStartTime(informationEntities.get(i).getStartTime());
            informationModel.setSite(informationEntities.get(i).getSite());
            informationModel.setCertificateType(informationEntities.get(i).getCertificateType());
            informationModel.setCertificateNumber(informationEntities.get(i).getCertificateNumber());
            informationModel.setPhoneNumber(informationEntities.get(i).getPhoneNumber());
            informationModel.setAge(informationEntities.get(i).getAge());
            informationModel.setSex(informationEntities.get(i).getSex());
            informationModel.setHouseholdAddress(informationEntities.get(i).getHouseholdAddress());
            informationModel.setTobaccoNumber(informationEntities.get(i).getTobaccoNumber());
            informationModel.setTheRealAddress(informationEntities.get(i).getTheRealAddress());
            informationModel.setBuckleSingleNumber(informationEntities.get(i).getBuckleSingleNumber());
            informationModel.setTheCaseNumber(informationEntities.get(i).getTheCaseNumber());
            informationModel.setLe(informationEntities.get(i).getLe());
            informationModel.setYear(informationEntities.get(i).getYear());
            informationModel.setClassificationId(informationEntities.get(i).getClassificationId());
            informationModel.setName(informationEntities.get(i).getName());
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
        informationModel.setSeizedTime(informationEntity.getSeizedTime());
        informationModel.setStartTime(informationEntity.getStartTime());
        informationModel.setSite(informationEntity.getSite());
        informationModel.setCertificateType(informationEntity.getCertificateType());
        informationModel.setCertificateNumber(informationEntity.getCertificateNumber());
        informationModel.setPhoneNumber(informationEntity.getPhoneNumber());
        informationModel.setAge(informationEntity.getAge());
        informationModel.setSex(informationEntity.getSex());
        informationModel.setHouseholdAddress(informationEntity.getHouseholdAddress());
        informationModel.setTobaccoNumber(informationEntity.getTobaccoNumber());
        informationModel.setTheRealAddress(informationEntity.getTheRealAddress());
        informationModel.setBuckleSingleNumber(informationEntity.getBuckleSingleNumber());
        informationModel.setTheCaseNumber(informationEntity.getTheCaseNumber());
        informationModel.setLe(informationEntity.getLe());
        informationModel.setYear(informationEntity.getYear());
        informationModel.setClassificationId(informationEntity.getClassificationId());
        informationModel.setName(informationEntity.getName());
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
        informationEntity.setSeizedTime(informationModel.getSeizedTime());
        informationEntity.setStartTime(informationModel.getStartTime());
        informationEntity.setSite(informationModel.getSite());
        informationEntity.setCertificateType(informationModel.getCertificateType());
        informationEntity.setCertificateNumber(informationModel.getCertificateNumber());
        informationEntity.setPhoneNumber(informationModel.getPhoneNumber());
        informationEntity.setAge(informationModel.getAge());
        informationEntity.setSex(informationModel.getSex());
        informationEntity.setHouseholdAddress(informationModel.getHouseholdAddress());
        informationEntity.setTobaccoNumber(informationModel.getTobaccoNumber());
        informationEntity.setTheRealAddress(informationModel.getTheRealAddress());
        informationEntity.setBuckleSingleNumber(informationModel.getBuckleSingleNumber());
        informationEntity.setTheCaseNumber(informationModel.getTheCaseNumber());
        informationEntity.setLe(informationModel.getLe());
        informationEntity.setYear(informationModel.getYear());
        informationEntity.setClassificationId(informationModel.getClassificationId());
        informationEntity.setName(informationModel.getName());
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

    @Override
    public void getInformation() {
        List<InformationEntity> informationEntities = informationMapper.selectList(new QueryWrapper<>());
        for (int i = 0;i<informationEntities.size();i++){
            System.out.println(informationEntities.get(i));
        }
    }

    @Override
    public InformationModel getInformationFoName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("name",name);
        InformationEntity informationEntity = informationMapper.selectOne(queryWrapper);
        InformationModel informationModel = new InformationModel();
        informationEntity.setId(informationModel.getId());
        informationEntity.setSeizedTime(informationModel.getSeizedTime());
        informationEntity.setStartTime(informationModel.getStartTime());
        informationEntity.setSite(informationModel.getSite());
        informationEntity.setCertificateType(informationModel.getCertificateType());
        informationEntity.setCertificateNumber(informationModel.getCertificateNumber());
        informationEntity.setPhoneNumber(informationModel.getPhoneNumber());
        informationEntity.setAge(informationModel.getAge());
        informationEntity.setSex(informationModel.getSex());
        informationEntity.setHouseholdAddress(informationModel.getHouseholdAddress());
        informationEntity.setTobaccoNumber(informationModel.getTobaccoNumber());
        informationEntity.setTheRealAddress(informationModel.getTheRealAddress());
        informationEntity.setBuckleSingleNumber(informationModel.getBuckleSingleNumber());
        informationEntity.setTheCaseNumber(informationModel.getTheCaseNumber());
        informationEntity.setLe(informationModel.getLe());
        informationEntity.setYear(informationModel.getYear());
        informationEntity.setClassificationId(informationModel.getClassificationId());
        informationEntity.setName(informationModel.getName());
        return informationModel;
    }
}
