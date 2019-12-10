package com.rootwish.bilu.controller;

import com.rootwish.bilu.model.Smoke;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController
public class TableViewController implements Initializable {

    @FXML
    private TableView tableView;

//    @FXML
//    private TableView<Smoke> tableView = new TableView<Smoke>();

    @FXML
    private TableColumn type ,num, price;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Smoke> list = FXCollections.observableArrayList();
        Smoke smoke = new Smoke();//构建值对象
        smoke.setType("小六");
        smoke.setNum("123");
        smoke.setPrice("233");
        type.setCellValueFactory(new PropertyValueFactory("type"));//映射
        num.setCellValueFactory(new PropertyValueFactory("num"));
        price.setCellValueFactory(new PropertyValueFactory("price"));
        list.add(smoke);        //list添加值对象
        tableView.setItems(list); //tableview添加list
    }
}
