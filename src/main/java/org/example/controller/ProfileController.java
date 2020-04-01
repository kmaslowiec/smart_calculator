package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private HBox buttonsBox;
    @FXML
    private VBox infoBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonsBox.setSpacing(15.0);
        infoBox.setSpacing(15.0);
    }
}
