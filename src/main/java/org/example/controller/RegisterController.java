package org.example.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.App;
import org.example.entity.User;
import org.example.exception.InvalidEntryException;
import org.example.model.RegistrationModel;
import org.example.model.impl.RegistrationModelImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class RegisterController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    @FXML
    public TextField emailField;
    @FXML
    public TextField repeatPassField;
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
    private RegistrationModel service = new RegistrationModelImpl();

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
            fieldsCannotBeEmpty();
            passwordsDoesNotMatch();
            boolean response = service.create(user);
            if (response) {
                registered();
            } else {
                failureMessage("User already exists");
            }
        } catch (InvalidEntryException e) {
            failureMessage(e.getMessage());
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() {
                    try {
                        Thread.sleep(2000);
                        App.setRoot("register");
                    } catch (InterruptedException | IOException ex) {
                        LOGGER.info(ex.getMessage());
                    }
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    private void registered() {
        registerButton.setVisible(false);
        successLabel.setText("Register successful");
        successLabel.setVisible(true);
        failureLabel.setVisible(false);
        try {
            App.setRoot("login");
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }

    private void failureMessage(String msg) {
        failureLabel.setVisible(true);
        failureLabel.setText(msg);
    }

    private void fieldsCannotBeEmpty() throws InvalidEntryException {
        failureLabel.setVisible(true);
        if (loginField.getText().trim().isEmpty() || passField.getText().trim().isEmpty()) {
            LOGGER.info("fields can't be empty");
            throw new InvalidEntryException("fields can't be empty");
        }
    }

    private void passwordsDoesNotMatch() throws InvalidEntryException {
        if (!passField.getText().equals(repeatPassField.getText())) {
            LOGGER.info("Password and confirm password does not match");
            throw new InvalidEntryException("Password and confirm password does not match");
        }
    }

    private void passwordFieldEncapsulation() {

    }
}