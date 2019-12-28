package com.rootwish.bilu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rootwish.bilu.entity.ClassificationEntity;
import com.rootwish.bilu.entity.InformationEntity;
import com.rootwish.bilu.entity.RecordEntity;
import com.rootwish.bilu.model.InformationModel;
import com.rootwish.bilu.service.ClassificationService;
import com.rootwish.bilu.service.InformationService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


@FXMLController
public class InfoListController implements Initializable {
    @FXML
    private TreeView treeView;

    @FXML
    private TableView infoList;
    @FXML
    private TableColumn name,theCaseNumber;

    @Autowired
    private InformationService realInformationService;
    @Autowired
    private ClassificationService relalClassificationService;

    private static InformationService informationService;
    private static ClassificationService classificationService;

    @PostConstruct
    public void init() {
        informationService = realInformationService;
        classificationService = relalClassificationService;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> rootItem = new TreeItem<> ("分类");
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

        QueryWrapper<InformationEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("classification_id",classificationId);
        List<InformationEntity> list = informationService.list();
        ObservableList<InformationEntity> list1 = infoList.getItems();
        name.setCellValueFactory(new PropertyValueFactory("name"));
        theCaseNumber.setCellValueFactory(new PropertyValueFactory("theCaseNumber"));
        for(InformationEntity informationEntity: list){//映射
            list1.add(informationEntity);        //list添加值对象
        }
        infoList.setItems(list1);

        infoList.setRowFactory( tv -> {
            TableRow<InformationEntity> row = new TableRow<InformationEntity>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    InformationEntity info = row.getItem();
                    TreeViewController treeViewController = new TreeViewController();
                    treeViewController.showStage(info);
                }
            });
            return row ;
        });
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
                    QueryWrapper queryWrapper1 = new QueryWrapper();
                    if(null == classificationEntity) {
                        infoList.setItems(null);
                        return;
                    }
                    queryWrapper1.eq("classification_id", classificationEntity.getId());
                    List<InformationEntity> list2 = informationService.list(queryWrapper1);
                    if(list2.size() == 0) {
                        infoList.setItems(null);
                        return;
                    }
                    ObservableList<InformationEntity> strList = FXCollections.observableArrayList();
                    for(InformationEntity informationEntity: list2){//映射
                        strList.add(informationEntity);        //list添加值对象
                    }
                    infoList.setItems(strList);
                }
            }
        });

    }

    public void showStage() {
        Stage secondWindow=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InfoList.fxml"));
        Scene scene= null;
        try {
            scene = new Scene(loader.load(),1000,500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        secondWindow.setScene(scene);
        secondWindow.showAndWait();
    }
}
