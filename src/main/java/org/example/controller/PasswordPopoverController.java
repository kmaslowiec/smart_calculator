package org.example.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import org.example.entity.User;
import org.example.exception.InvalidEntryException;
import org.example.model.InMemory;
import org.example.model.UserDao;
import org.example.model.impl.LoginModelImpl;
import org.example.utils.MyStrings;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PasswordPopoverController extends ProfileController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(PasswordPopoverController.class.getName());

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField repeatPassField;

    @FXML
    private PasswordField currentPassword;

    @FXML
    private Label infoLabel;

    private LoginModelImpl model;
    private InMemory memory;
    private UserDao dao;
    private ViewHelper viewHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memory = new InMemory();
        viewHelper = new ViewHelper();
        dao = new UserDao();
        model = new LoginModelImpl();
    }

    public void change(ActionEvent actionEvent) {
        try {
            User user = memory.getUser();
            if (!model.isAuthorized(currentPassword.getText(), user)) throw new InvalidEntryException(MyStrings.WRONG_PASSWORD);
            ViewHelper.fieldsCannotBeEmpty(infoLabel, new ArrayList<>(Arrays.asList(passField, repeatPassField)), LOGGER);
            viewHelper.passwordsDoesNotMatch(passField, repeatPassField, LOGGER);
            user.setPassword(repeatPassField.getText());
            dao.update(user);
            infoLabel.setTextFill(Color.GREEN);
            infoLabel.setText("Success");
            success();
            ProfileController.popOver.hide();
        } catch (InvalidEntryException e) {
            ViewHelper.failureMessage(infoLabel, e.getMessage());
            refreshPopOver();
        }
    }

    private void refreshPopOver() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(2000);
                    infoLabel.setVisible(false);
                    repeatPassField.setText("");
                    passField.setText("");
                    currentPassword.setText("");
                } catch (InterruptedException ex) {
                    LOGGER.info(ex.getMessage());
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    private void success() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(300);
                    ProfileController.popOver.hide();
                } catch (InterruptedException ex) {
                    LOGGER.info(ex.getMessage());
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}