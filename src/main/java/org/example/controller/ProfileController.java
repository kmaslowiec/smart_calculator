package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.utils.InMemory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private TextField email;

    @FXML
    private TextField login;

    @FXML
    private HBox buttonsBox;

    @FXML
    private VBox infoBox;

    private InMemory memory = new InMemory();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonsBox.setSpacing(15.0);
        infoBox.setSpacing(15.0);
        memory = new InMemory();
        fillTheFields();
    }

    public void fillTheFields() {
        login.setText(memory.getUser().getName());
        email.setText(memory.getUser().getEmail());
    }
}