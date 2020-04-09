package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryDisplayController implements Initializable {

    @FXML
    private TextField dateField;

    @FXML
    private TextArea historyArea;

    @FXML
    private VBox dateBox;

    private ViewHelper viewHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateBox.setSpacing(15.0);
        viewHelper = new ViewHelper();
    }

    public void showHistory(ActionEvent actionEvent) {
        String date = dateField.getText();
        System.out.println(viewHelper.isDateValidFormat(date));
    }
}
