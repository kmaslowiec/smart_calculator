package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.entity.User;
import org.example.exception.InvalidEntryException;
import org.example.service.RegistrationService;
import org.example.service.impl.RegistrationServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class RegisterController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    @FXML
    public TextField emailField;
    @FXML
    private TextField loginField;
    @FXML
    private VBox form;
    @FXML
    private Button registerButton;
    @FXML
    private Label successLabel;
    @FXML
    private Label failureLabel;
    @FXML
    private TextField passField;
    private RegistrationService service = new RegistrationServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        form.setSpacing(15.0);
        successLabel.setVisible(false);
    }

    public void register(ActionEvent actionEvent) {
        try {
            User user = new User();
            user.setName(loginField.getText());
            user.setPassword(passField.getText());
            user.setEmail(emailField.getText());
            boolean response = service.create(user);
            if (response) {
                registered();
            } else {
                notRegistered();
            }
        } catch (InvalidEntryException e) {
            failureLabel.setVisible(true);
            failureLabel.setText(e.getMessage());
        }
    }

    private void registered() {
        registerButton.setVisible(false);
        successLabel.setText("You've been registered");
        successLabel.setVisible(true);
        failureLabel.setVisible(false);
    }

    private void notRegistered() {
        failureLabel.setVisible(true);
        failureLabel.setText("User already exists");
    }
}