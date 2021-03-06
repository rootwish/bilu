package com.rootwish.bilu.service.impl;

import com.rootwish.bilu.entity.SmokeEntity;
import com.rootwish.bilu.model.InformationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/14
 */
public class demo {
    public static void main(String[] args) {
        FreeMarkerWordServiceImpl freeMarkerWordService = new FreeMarkerWordServiceImpl();
        InformationModel informationModel = new InformationModel();
        informationModel.setName("张三");
        informationModel.setSeizedTime("2019年11月11日");
        informationModel.setStartTime("2019年11月12日");
        informationModel.setSite("第三医院");
        informationModel.setCertificateType("身份证");
        informationModel.setCertificateNumber("440971188605170371");
        informationModel.setPhoneNumber("13567803454");
        informationModel.setAge("45");
        informationModel.setSex("男");
        informationModel.setHouseholdAddress("地球的某一个角落");
        informationModel.setTheRealAddress("角落里的角落");
        informationModel.setBuckleSingleNumber("扣单编号");
        informationModel.setTheCaseNumber("79");
        List<SmokeEntity> smokeEntityList = new ArrayList<>();
        SmokeEntity smokeEntity = new SmokeEntity();
        smokeEntity.setId(1);
        smokeEntity.setSmokeName("红双喜（软）");
        smokeEntity  .setPackOfNumber("100");
        smokeEntity.setRetailPrice("60");
        smokeEntity.setInformationId(1);
        smokeEntity.setNumberTotalPrice("6000");
        smokeEntity.setPackageStyle("条盒硬盒");
        smokeEntity.setSampleBase("5");
        smokeEntity.setAmplingNumber("2");
        smokeEntity.setTiaoBaoBarCode("11111111111");
        SmokeEntity smokeEntity2 = new SmokeEntity();
        smokeEntity2.setId(2);
        smokeEntity2.setSmokeName("经典（软）");
        smokeEntity2.setPackOfNumber("100");
        smokeEntity2.setRetailPrice("100");
        smokeEntity2.setInformationId(1);
        smokeEntity2.setNumberTotalPrice("10000");
        smokeEntity2.setPackageStyle("条盒软盒");
        smokeEntity2.setSampleBase("100");
        smokeEntity2.setAmplingNumber("50");
        smokeEntity2.setTiaoBaoBarCode("22222222222");
        smokeEntityList.add(smokeEntity);
        smokeEntityList.add(smokeEntity2);
        informationModel.setSmoke(smokeEntityList);
        informationModel.setLe("乐");
        informationModel.setYear("2018");
        informationModel.setClassificationId(1);
        informationModel.setTobaccoNumber("11111111111111111");
        freeMarkerWordService.exporMillCertificateWord(informationModel);
    }
}
