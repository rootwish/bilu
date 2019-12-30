package com.rootwish.bilu.controller;

import com.rootwish.bilu.model.ClassificationModel;
import com.rootwish.bilu.model.RecordModel;
import com.rootwish.bilu.service.ClassificationService;
import com.rootwish.bilu.service.RecordService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 类的描述
 *
 * @author SZJ
 * @date 2019/12/23
 */
@FXMLController
public class RecordController implements Initializable {
    @FXML
    private TextArea record;
    @FXML
    private ChoiceBox classification;
    @FXML
    private javafx.scene.control.Button save;

    @Autowired
    private RecordService ATWrecordService;

    @Autowired
    private ClassificationService AUTclassificationService;

    private static RecordService recordService;
    private static ClassificationService classificationService;

    @PostConstruct
    public void init(){
        recordService = ATWrecordService;
        classificationService = AUTclassificationService;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final String[] greetings =  new String[]{"无证运输","非渠道购进","假烟无证运输","烟叶烟丝"};
        classification.getItems().addAll("无证运输","非渠道购进","假烟无证运输","烟叶烟丝");
        /*RecordModel recordModel = recordService.getRecordFoInformationId(1);
        record.appendText(recordModel.getRecord());*/
        classification.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int selectedIndex = classification.getSelectionModel().getSelectedIndex();
                ClassificationModel classification = classificationService.getClassification(greetings[selectedIndex]);
                RecordModel recordFoClassificationID = recordService.getRecordFoClassificationID(classification.getId());
                record.setText(recordFoClassificationID.getRecord());
            }
        });

        save.setOnAction((ActionEvent e) -> {
            int selectedIndex = classification.getSelectionModel().getSelectedIndex();
            String classifyName = greetings[selectedIndex];
            ClassificationModel classification = classificationService.getClassification(classifyName);
            RecordModel recordFoClassificationID = recordService.getRecordFoClassificationID(classification.getId());
            String text = record.getText();
            recordFoClassificationID.setRecord(text);
            boolean b = recordService.UpdataRecord(recordFoClassificationID);
            System.out.println(b);
        });
    }

    public void showStage() {
        Stage secondWindow=new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/record.fxml"));
        Scene scene= null;
        secondWindow.setTitle("笔录模板");
        try {
            scene = new Scene(loader.load(),850,650);
        } catch (IOException e) {
            e.printStackTrace();
        }
        secondWindow.setScene(scene);
        secondWindow.showAndWait();
    }
}
