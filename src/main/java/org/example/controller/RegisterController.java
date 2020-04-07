package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.App;
import org.example.entity.User;
import org.example.exception.InvalidEntryException;
import org.example.model.UserDao;
import org.example.utils.MyStrings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class RegisterController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(RegisterController.class.getName());
    private static final String THIS_FXML = "register";
    private static final String DIRECT_TO = "login";

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField repeatPassField;

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
    private PasswordField passField;

    private UserDao dao;
    private ViewHelper viewHelper;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        form.setSpacing(15.0);
        successLabel.setVisible(false);
        viewHelper = new ViewHelper();
        dao = new UserDao();
    }

    public void register(ActionEvent actionEvent) {
        try {
            User user = new User();
            user.setName(loginField.getText());
            user.setPassword(passField.getText());
            user.setEmail(emailField.getText());

            ViewHelper.fieldsCannotBeEmpty(failureLabel, new ArrayList<>(Arrays.asList(loginField, passField)), LOGGER);
            viewHelper.passwordsDoesNotMatch(repeatPassField, passField, LOGGER);

            boolean response = viewHelper.emailValidation(emailField.toString(), LOGGER);
            if (response) {
                dao.create(user);
                registered();
            } else {
                ViewHelper.failureMessage(failureLabel, MyStrings.USER_EXISTS);
            }
        } catch (InvalidEntryException e) {
            ViewHelper.failureMessage(failureLabel, e.getMessage());
            ViewHelper.refreshScene(THIS_FXML, LOGGER);
        }
    }

    private void registered() {
        registerButton.setVisible(false);
        successLabel.setText(MyStrings.REGISTRATION_SUCCESS);
        successLabel.setVisible(true);
        failureLabel.setVisible(false);
        try {
            App.setRoot(DIRECT_TO);
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
    }
}