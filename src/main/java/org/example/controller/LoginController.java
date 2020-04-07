package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.App;
import org.example.entity.User;
import org.example.exception.InvalidEntryException;
import org.example.model.InMemory;
import org.example.model.LoginModel;
import org.example.model.impl.LoginModelImpl;
import org.example.utils.MyRegex;
import org.example.utils.MyStrings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    private static final String THIS_FXML = "login";
    private static final String DIRECT_TO = "calculator";

    @FXML
    private Label failureLabel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private VBox form;

    private LoginModel model;
    private int attempts;
    private InMemory memory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        form.setSpacing(15.0);
        model = new LoginModelImpl();
        memory = new InMemory();
    }

    public void login(ActionEvent actionEvent) {
        try {
            ViewHelper.fieldsCannotBeEmpty(failureLabel, new ArrayList<>(Arrays.asList(loginField, passField)), LOGGER);
            User user = new User();
            String login = loginField.getText();
            if (login.matches(MyRegex.EMAIL_IS_VALID)) {
                user.setEmail(login);
            } else {
                user.setName(login);
            }
            user.setPassword(passField.getText());
            User authorizedUser = model.getUserIfAuthorized(user);

            if (authorizedUser != null) {
                try {
                    memory.setUser(authorizedUser.getId());
                    App.setRoot(DIRECT_TO);
                } catch (IOException e) {
                    LOGGER.info(e.getMessage());
                }
            } else {
                attempts++;
                if (attempts == 3) {
                    System.exit(0);
                }
                throw new InvalidEntryException(MyStrings.ACCESS_DENIED);
            }
        } catch (InvalidEntryException e) {
            ViewHelper.failureMessage(failureLabel, e.getMessage());
            ViewHelper.refreshScene(THIS_FXML, LOGGER);
        }
    }
}