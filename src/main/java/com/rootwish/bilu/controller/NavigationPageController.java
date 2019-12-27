package com.rootwish.bilu.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class NavigationPageController implements Initializable {

    @FXML
    private static TreeViewController treeViewController = new TreeViewController();

    @FXML
    private static InfoListController infoListController = new InfoListController();

    @FXML
    private Button addInfo,infoList;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        addInfo.setOnAction((ActionEvent e) -> {
//            TreeViewController treeViewController = new TreeViewController();
            treeViewController.showStage(null);
        });
        infoList.setOnAction((ActionEvent e) -> {
//            InfoListController infoListController = new InfoListController();
            infoListController.showStage();
        });
    }


}
