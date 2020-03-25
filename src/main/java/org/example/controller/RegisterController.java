package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.RegistrationModel;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    public TextField loginField;
    @FXML
    public VBox form;
    @FXML
    public Button registerButton;
    @FXML
    public Label infoLabel;
    @FXML
    public Label notRegInfo;
    @FXML
    public TextField passField;
    private RegistrationModel loginModel = new RegistrationModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        form.setSpacing(15.0);
        infoLabel.setVisible(false);
    }

    public void register(ActionEvent actionEvent) {
        String login = loginField.getText();
        String password = passField.getText();
        boolean isRegistered = loginModel.insert(login, password);
        if (isRegistered) {
            registered();
        } else {
            notRegistered();
        }
    }

    private void registered() {
        registerButton.setVisible(false);
        infoLabel.setText("You've been registered");
        infoLabel.setVisible(true);
        notRegInfo.setVisible(false);
    }

    private void notRegistered() {
        notRegInfo.setVisible(true);
        notRegInfo.setText("User already exists");
    }
}