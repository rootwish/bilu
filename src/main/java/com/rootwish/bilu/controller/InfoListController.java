package com.rootwish.bilu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rootwish.bilu.entity.InformationEntity;
import com.rootwish.bilu.model.InformationModel;
import com.rootwish.bilu.service.InformationService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
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
    private static InformationService informationService;

    @PostConstruct
    public void init() {
        informationService = realInformationService;
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
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
