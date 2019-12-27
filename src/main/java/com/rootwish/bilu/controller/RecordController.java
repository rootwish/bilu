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
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RecordService recordService;

    @Autowired
    private ClassificationService classificationService;




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
                record.appendText(recordFoClassificationID.getRecord());
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
}