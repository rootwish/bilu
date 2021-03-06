package com.rootwish.bilu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rootwish.bilu.BiluApplication;
import com.rootwish.bilu.entity.ClassificationEntity;
import com.rootwish.bilu.entity.InformationEntity;
import com.rootwish.bilu.entity.RecordEntity;
import com.rootwish.bilu.entity.SmokeEntity;
import com.rootwish.bilu.model.InformationModel;
import com.rootwish.bilu.model.Smoke;
import com.rootwish.bilu.service.ClassificationService;
import com.rootwish.bilu.service.InformationService;
import com.rootwish.bilu.service.RecordService;
import com.rootwish.bilu.service.impl.FreeMarkerWordServiceImpl;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;
import java.io.File;
import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 中文
 */
@Component
@FXMLController
public class TreeViewController implements Initializable {

    @FXML
    private TreeView treeView;

    @FXML
    private TableView tableView;

    @FXML
    private ListView recordList;

    @FXML
    private TableColumn type ,num, price,code;

    @FXML
    private Button add,save,preview;

    @FXML
    private ChoiceBox sex,certificateType;

    @FXML
    private DatePicker startTime,seizedTime;

    @FXML
    private TextArea record;

    @FXML
    private TextField name, site, certificateNumber, phoneNumber, age, householdAddress,
            theRealAddress, theCaseNumber, newType, newNum, newPrice, newCode,buckleSingleNumber,tobaccoNumber;

    @Autowired
    private InformationService realInformationService;
    @Autowired
    private RecordService realRecordService;
    @Autowired
    private ClassificationService relalClassificationService;

    private static InformationService informationService;
    private static RecordService recordService;
    private static ClassificationService classificationService;

    private static InformationEntity informationEntity;
    private static String classfiName;

    @PostConstruct
    public void init() {
        informationService = realInformationService;
        recordService = realRecordService;
        classificationService = relalClassificationService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //根据分类读取笔录模板
        if(null != informationEntity) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("classification_id", informationEntity.getClassificationId());
            RecordEntity recordEntity = recordService.getOne(queryWrapper);

            ObservableList<String> strList = FXCollections.observableArrayList(Arrays.asList(recordEntity.getRecord().replace("问", "**问").split("\\*\\*")));
            recordList.setItems(strList);
        }


        recordList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event)
            {
                if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    int index = recordList.getSelectionModel().getSelectedIndex();
                    record.setText(record.getText() + "\n" + recordList.getItems().get(index));
                }
            }
        });
        
        TreeItem<String> rootItem = new TreeItem<> ("全部");
        treeView.setRoot(rootItem);
        rootItem.setExpanded(true);
        TreeItem<String> item = new TreeItem<> ("无证运输");
//        TreeItem<String> item1 = new TreeItem<> ("货主笔录");
//        TreeItem<String> item2 = new TreeItem<> ("司机+货主笔录");
//        TreeItem<String> item3 = new TreeItem<> ("司机笔录");
//        item.getChildren().add(item1);
//        item.getChildren().add(item2);
//        item.getChildren().add(item3);
        rootItem.getChildren().add(item);
        item = new TreeItem<> ("非渠道购进");
        rootItem.getChildren().add(item);
        item = new TreeItem<> ("假烟无证运输");
        rootItem.getChildren().add(item);
        item = new TreeItem<> ("烟叶烟丝");
        rootItem.getChildren().add(item);
        TreeView<String> tree = new TreeView<> (rootItem);

        sex.getItems().addAll("男", "女");
        sex.getSelectionModel().selectFirst();
        certificateType.getItems().addAll("身份证","驾驶证","暂住证","临时身份证");
        certificateType.getSelectionModel().selectFirst();


        //分类树点击事件
//        treeView.focusModelProperty().setValue(item);
//        treeView.setFocusModel( );
        treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event)
            {
                Node node = event.getPickResult().getIntersectedNode();
                if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
                    String name = (String) ((TreeItem)treeView.getSelectionModel().getSelectedItem()).getValue();
                    System.out.println("Node click: " + name);
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("classify_name", name);
                    ClassificationEntity classificationEntity = classificationService.getOne(queryWrapper);
                    classfiName = name;
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    if(null == classificationEntity) {
                        recordList.setItems(null);
                        return;
                    }
                    queryWrapper1.eq("classification_id", classificationEntity.getId());
                    RecordEntity recordEntity = recordService.getOne(queryWrapper1);
                    if(null == recordEntity) {
                        recordList.setItems(null);
                        return;
                    }
                    ObservableList<String> strList = FXCollections.observableArrayList(Arrays.asList(recordEntity.getRecord().replace("问", "**问").split("\\*\\*")));
                    recordList.setItems(strList);

                }
            }
        });

        add.setOnAction((ActionEvent e) -> {
            if(newType.getText() !=null && newNum.getText() != null && newPrice.getText() != null && newCode.getText() != null) {
                ObservableList<Smoke> list = tableView.getItems();
                Smoke smoke = new Smoke();//构建值对象
                smoke.setType(newType.getText());
                smoke.setNum(newNum.getText());
                smoke.setPrice(newPrice.getText());
                smoke.setCode(newCode.getText());
                type.setCellValueFactory(new PropertyValueFactory("type"));//映射
                num.setCellValueFactory(new PropertyValueFactory("num"));
                price.setCellValueFactory(new PropertyValueFactory("price"));
                code.setCellValueFactory(new PropertyValueFactory("code"));
                list.add(smoke);        //list添加值对象
                tableView.setItems(list); //tableview添加list
            }
        });

        save.setOnAction((ActionEvent e) -> {
            FreeMarkerWordServiceImpl freeMarkerWordService = new FreeMarkerWordServiceImpl();
            InformationModel informationModel = new InformationModel();
            informationModel.setName(name.getText());
            if(null != seizedTime.getValue()) {
                String newSeizedTime = seizedTime.getValue().toString()
                        .replaceFirst("-", "年")
                        .replace("-", "月") + "日";
                informationModel.setSeizedTime(newSeizedTime);
            }
            if(null != startTime.getValue()) {
                String newStartTime = startTime.getValue().toString()
                        .replaceFirst("-", "年")
                        .replace("-", "月") + "日";
                informationModel.setStartTime(newStartTime);
            }
            informationModel.setClassificationName(classfiName);
            informationModel.setSite(site.getText());
            informationModel.setCertificateType(certificateType.getValue().toString());
            informationModel.setCertificateNumber(certificateNumber.getText());
            informationModel.setPhoneNumber(phoneNumber.getText());
            informationModel.setAge(age.getText());
            informationModel.setSex(sex.getValue().toString());
            informationModel.setHouseholdAddress(householdAddress.getText());
            informationModel.setTheRealAddress(theRealAddress.getText());
            informationModel.setBuckleSingleNumber("扣单编号");
            informationModel.setTheCaseNumber(theCaseNumber.getText());

            //获取选中节点
            TreeItem oldItem =(TreeItem)treeView.getSelectionModel().getSelectedItem();

            informationModel.setRecord(record.getText());
            informationModel.setNote(record.getText());

            List<SmokeEntity> smokeEntityList = new ArrayList<>();
            SmokeEntity smokeEntity = new SmokeEntity();
            List<Smoke> list = tableView.getItems();
            for(Smoke s:list) {
                smokeEntity.setSmokeName(s.getType());
                smokeEntity.setPackOfNumber(s.getNum());
                smokeEntity.setRetailPrice(s.getPrice());
                smokeEntity.setInformationId(1);
                smokeEntity.setNumberTotalPrice(String.valueOf(Double.valueOf(s.getNum())*Double.valueOf(s.getPrice())));
                smokeEntity.setPackageStyle("条盒硬盒");
                smokeEntity.setSampleBase("5");
                smokeEntity.setAmplingNumber("2");
                smokeEntity.setTiaoBaoBarCode(s.getCode());
                smokeEntityList.add(smokeEntity);
            }
            informationModel.setSmoke(smokeEntityList);
            informationModel.setLe("乐");
            informationModel.setYear("2018");
            informationModel.setClassificationId(1);
            informationModel.setTobaccoNumber("11111111111111111");
            freeMarkerWordService.exporMillCertificateWord(informationModel);
            boolean b = informationService.saveInformation(informationModel);
            System.out.println(b);
//            realInformationService.getInformation();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("保存成功！");
            alert.setHeaderText("保存文档于程序目录workDoc下");
//            alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();
        });
        preview.setOnAction((ActionEvent e)->{
            /*File file = new File("D:\\work\\bilu\\bilu\\src\\main\\resources\\template");
            JFileChooser jFileChooser = new JFileChooser(file);
            jFileChooser.showOpenDialog(null);*/
            System.out.println(System.getProperty("user.dir")+"\\workDoc");
            try {
                Runtime.getRuntime().exec("explorer /e,/root,"+System.getProperty("user.dir")+"\\workDoc");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        if (null != informationEntity){
            InformationModel information = informationService.getInformation(informationEntity.getId());
            //查获时间
            //seizedTime.setValue();
            //笔录开始时间
            String time = information.getStartTime().replace("年","-").replace("月","-").replace("日","");
                LocalDate beginDateTime = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-M-d"));
            startTime.setValue(beginDateTime);

            //笔录结束时间


            //询问地点
            site.setText(information.getSite());
            //姓名
            name.setText(information.getName());
            //证件类型

            switch (information.getCertificateType()){
                case "身份证":
                    certificateType.getItems().clear();
                    certificateType.getItems().addAll(information.getCertificateType(),"驾驶证","暂住证","临时身份证");
                    certificateType.getSelectionModel().selectFirst();
                    break;
                case "驾驶证":
                    certificateType.getItems().clear();
                    certificateType.getItems().addAll(information.getCertificateType(),"身份证","暂住证","临时身份证");
                    certificateType.getSelectionModel().selectFirst();
                    break;
                case "暂住证":
                    certificateType.getItems().clear();
                    certificateType.getItems().addAll(information.getCertificateType(),"身份证","驾驶证","临时身份证");
                    certificateType.getSelectionModel().selectFirst();
                    break;
                case "临时身份证":
                    certificateType.getItems().clear();
                    certificateType.getItems().addAll(information.getCertificateType(),"身份证","暂住证","驾驶证");
                    certificateType.getSelectionModel().selectFirst();
                    break;
            }
            //证件号码
            certificateNumber.setText(information.getCertificateNumber());
            //电话号码
            phoneNumber.setText(information.getPhoneNumber());
            //年龄
            age.setText(information.getAge());
            String SEX = "男";
            if ("男".equals(information.getSex())){
                SEX = "女";
            }
            //性别
            sex.getItems().clear();
            sex.getItems().addAll(information.getSex(),SEX);
            sex.getSelectionModel().selectFirst();
            //户籍地址
            householdAddress.setText(information.getHouseholdAddress());
            //实际住址
            theRealAddress.setText(information.getTheRealAddress());
            //烟草证号
            tobaccoNumber.setText(information.getTobaccoNumber());
            //案件编号
            theCaseNumber.setText(information.getTheCaseNumber());
            //扣单编号
            buckleSingleNumber.setText(information.getBuckleSingleNumber());

            //smoke
            List<SmokeEntity> oldSmokeList = information.getSmoke();
            if(oldSmokeList.size() >0) {
                ObservableList<Smoke> list = FXCollections.observableArrayList();
                for (SmokeEntity smokeEntity:oldSmokeList) {
                    Smoke smoke = new Smoke();//构建值对象
                    smoke.setType(smokeEntity.getSmokeName());
                    smoke.setNum(smokeEntity.getPackOfNumber());
                    smoke.setPrice(smokeEntity.getRetailPrice());
                    smoke.setCode(smokeEntity.getTiaoBaoBarCode());
                    type.setCellValueFactory(new PropertyValueFactory("type"));//映射
                    num.setCellValueFactory(new PropertyValueFactory("num"));
                    price.setCellValueFactory(new PropertyValueFactory("price"));
                    code.setCellValueFactory(new PropertyValueFactory("code"));
                    list.add(smoke);        //list添加值对象
                }
                tableView.setItems(list); //tableview添加list
            }

            //笔录
            record.setText(information.getNote());
        }


    }

//    public TreeViewController(InformationEntity informationEntity) {
//        // We received the first controller, now let's make it usable throughout this controller.
//        this.controller1 = controller1;
//        // Create the new stage
//        thisStage = new Stage();
//        // Load the FXML file
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout2.fxml"));
//            // Set this class as the controller
//            loader.setController(this);
//            // Load the scene
//            thisStage.setScene(new Scene(loader.load()));
//            // Setup the window/stage
//            thisStage.setTitle("Passing Controllers Example - Layout2");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void showStage(InformationEntity informationEntity) {
        this.informationEntity = informationEntity;
        Stage secondWindow=new Stage();
        secondWindow.setTitle("案件信息");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IndexView.fxml"));
        Scene scene= null;
        try {
            scene = new Scene(loader.load(),1350,700);
        } catch (IOException e) {
            e.printStackTrace();
        }
        secondWindow.setScene(scene);
        secondWindow.showAndWait();
    }
}
