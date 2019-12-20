package com.rootwish.bilu.service.impl;

import com.rootwish.bilu.entity.SmokeEntity;
import com.rootwish.bilu.model.InformationModel;
import com.rootwish.bilu.service.FreeMarkerWordService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/13
 */
@Service
public class FreeMarkerWordServiceImpl implements FreeMarkerWordService {

    private static Configuration configuration = null;
    //获取模板文件的服务器位置
    //private static final String templateFolder = WordUtils.class.getClassLoader().getResource("../../").getPath() + "WEB-INF/templetes/";
    private static final String templateFolder = System.getProperty("user.dir")+"\\template\\";
    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        try {
            //根据位置加载模板
            configuration.setDirectoryForTemplateLoading(new File(templateFolder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void FreeMarkerWord() {
        throw new AssertionError();
    }

    @Override
    public void exporMillCertificateWord(InformationModel informationModel){
        List<String> list = new ArrayList<>();
        list.add("证据先行登记保存批准书.ftl");
        list.add("先行登记保存证据处理通知书.ftl");
        list.add("送 达 回 证.ftl");
        list.add("涉案专卖品价格证明申请表（新）样板.ftl");
        list.add("乐昌市烟草专卖局201年39号案有关情况汇报.ftl");
        list.add("打印2份（新）涉案烟草专卖品核价表.ftl");
        list.add("(新)证据提取单 指认的涉案违法卷烟照片及卷烟进货票据.ftl");
        list.add("(新)证据提取单 身份证、许可证.ftl");
        list.add("（新）立案报告表.ftl");
        list.add("(新)抽样取证物品清单.ftl");
        list.add("(2018)打印2份（新）涉案烟草专卖品核价表.ftl");
        list.add("询问笔录.ftl");
        File file = null;
        InputStream fin = null;
        FileOutputStream fos = null;
        HashMap<String,Object> map = new HashMap<>();
        //查获日期
        map.put("seizedTime",informationModel.getSeizedTime());
        //乐
        map.put("le",informationModel.getLe());
        //2019
        map.put("year",informationModel.getYear());
        //案件编号
        map.put("theCaseNumber",informationModel.getTheCaseNumber());
        List<SmokeEntity> smokeList = informationModel.getSmoke();
        map.put("smokeList",smokeList);
        map.put("name",informationModel.getName());
        map.put("sex",informationModel.getSex());
        map.put("phone",informationModel.getPhoneNumber());
        //证件类型
        map.put("certificateType",informationModel.getCertificateType());
        //证件编号
        map.put("certificateNumber",informationModel.getCertificateNumber());
        //住址(待确认)
        map.put("householdAddress",informationModel.getHouseholdAddress());
        //笔录时间
        map.put("startTime",informationModel.getStartTime());
        map.put("record",informationModel.getRecord());
        int totalNumber = 0;
        Double totalPrice = 0.0;
        StringBuilder legalCaseSmoke = new StringBuilder();
        for (int i = 0;i<smokeList.size();i++){
            totalNumber+=Integer.parseInt(smokeList.get(i).getPackOfNumber());
            totalPrice+=Double.valueOf(smokeList.get(i).getNumberTotalPrice());
            legalCaseSmoke.append(smokeList.get(i).getSmokeName());
            if (i<(smokeList.size()-1)){
                legalCaseSmoke.append(",");
            }else if(i ==(smokeList.size()-1)){
                legalCaseSmoke.append("。共计"+smokeList.size()+"个品种");
            }
        }
        //品种数量
        map.put("smokeSize",smokeList.size());
        //所有品种烟总条数
        map.put("totalNumber",totalNumber);
        //所有品种烟总价格
        map.put("totalPrice",totalPrice.toString());
        //xx万支
        map.put("wan",totalNumber*0.02);
        legalCaseSmoke.append(totalNumber+"条("+(totalNumber*0.02)+"万支)");
        //案件摘要
        map.put("legalCaseSmoke",legalCaseSmoke);
        for (String fileName : list) {
            try {
                //根据文件名在模板文件夹路径中找到模板
                Template freemarkerTemplate = configuration.getTemplate(fileName);
                //导出的文件.doc
                //String newTitle = (System.getProperty("user.dir")+"\\workDoc\\"+list.get(0)).substring(0,(System.getProperty("user.dir")+"\\workDoc\\"+list.get(0)).length()-1);
                String newTitle = (System.getProperty("user.dir")+"\\workDoc\\"+fileName).substring(0,(System.getProperty("user.dir")+"\\workDoc\\"+fileName).length()-1);

                if ("涉案专卖品价格证明申请表（新）样板.ftl".equals(fileName)){
                    newTitle = newTitle.replace(".ft", ".xls");
                }else {
                    newTitle = newTitle.replace(".ft", ".doc");
                }
                //根据模板生成临时文件
                file = createDoc(map,freemarkerTemplate);
                //读取生成的文件
                fin = new FileInputStream(file);
                int tempByte;
                byte[] buffer = new byte[512];//缓冲区

                fos = new FileOutputStream(newTitle);

                //将读取的内容放到缓冲区并且写到新文件中去
                while((tempByte = fin.read(buffer))!=-1){
                    fos.write(buffer,0,tempByte);//数组，开始位置，长度
                }
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(fin != null) fin.close();
                    if(fos != null) fos.close();
                    if(file != null) file.delete(); // 删除临时文件
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    //创建word文档
    private static File createDoc(Map<?, ?> dataMap, Template template) {
        String name =  "sellPlan.doc";
        File f = new File(name);
        Template t = template;
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }
}
