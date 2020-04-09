package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryDisplayController implements Initializable {

    @FXML
    private TextArea historyArea;

    @FXML
    private VBox dateBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateBox.setSpacing(15.0);
    }

    public void showHistory(ActionEvent actionEvent) {
        historyArea.setText("dupa");
    }
}
