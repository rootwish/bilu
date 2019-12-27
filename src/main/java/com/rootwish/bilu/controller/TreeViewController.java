package com.rootwish.bilu.controller;

import com.rootwish.bilu.BiluApplication;
import com.rootwish.bilu.entity.InformationEntity;
import com.rootwish.bilu.entity.SmokeEntity;
import com.rootwish.bilu.model.InformationModel;
import com.rootwish.bilu.model.Smoke;
import com.rootwish.bilu.service.InformationService;
import com.rootwish.bilu.service.impl.FreeMarkerWordServiceImpl;
import de.felixroske.jfxsupport.FXMLController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
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
    private Button add,save;

    @FXML
    private ChoiceBox sex,certificateType;

    @FXML
    private DatePicker startTime,seizedTime;

    @FXML
    private TextArea record;

    @FXML
    private TextField name, site, certificateNumber, phoneNumber, age, householdAddress,
            theRealAddress, theCaseNumber, newType, newNum, newPrice, newCode;


    @FXML
    private HTMLEditor htmlEditor;



    @Autowired
    private InformationService realInformationService;

    private static InformationService informationService;

    private static InformationEntity informationEntity;

    @PostConstruct
    public void init() {
        informationService = realInformationService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        ObservableList<String> strList = FXCollections.observableArrayList();
//        recordList.setItems(strList);
        
        TreeItem<String> rootItem = new TreeItem<> ("分类");
        treeView.setRoot(rootItem);
        rootItem.setExpanded(true);
        TreeItem<String> item = new TreeItem<> ("无证运输");
        TreeItem<String> item1 = new TreeItem<> ("货主笔录");
        TreeItem<String> item2 = new TreeItem<> ("司机+货主笔录");
        TreeItem<String> item3 = new TreeItem<> ("司机笔录");
        item.getChildren().add(item1);
        item.getChildren().add(item2);
        item.getChildren().add(item3);
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

            informationModel.setRecord(record.getText());

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/IndexView.fxml"));
        Scene scene= null;
        try {
            scene = new Scene(loader.load(),500,500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        secondWindow.setScene(scene);
        secondWindow.showAndWait();
    }
}
