package com.rootwish.bilu.controller;

import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 中文
 */
@FXMLController
public class IndexController implements Initializable {

    @FXML
    private TreeView treeView;

    @Override
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
    }

}
