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
    private TreeViewController treeViewController;

    @FXML
    private Button addInfo;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        addInfo.setOnAction((ActionEvent e) -> {
            TreeViewController treeViewController = new TreeViewController();
            treeViewController.showStage();
            System.out.println("111");
        });

//        addInfo.setOnAction(event -> treeViewController.showStage());
    }


}
