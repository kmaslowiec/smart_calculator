package org.example.controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.example.App;
import org.example.entity.User;
import org.example.exception.InvalidEntryException;
import org.example.model.InMemory;
import org.example.model.UserDao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PasswordPopoverController extends ProfileController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(PasswordPopoverController.class.getName());
    //private static final String THIS_FXML = "password_popover";

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField repeatPassField;

    @FXML
    private Label successLabel;

    @FXML
    private Button changeButton;

    @FXML
    private Label failureLabel;

    private InMemory memory;
    private UserDao dao;
    private ViewHelper viewHelper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memory = new InMemory();
        viewHelper = new ViewHelper();
        dao = new UserDao();
    }

    public void change(ActionEvent actionEvent) {
        try {
            User user = memory.getUser();

            ViewHelper.fieldsCannotBeEmpty(failureLabel, new ArrayList<>(Arrays.asList(passField, repeatPassField)), LOGGER);
            viewHelper.passwordsDoesNotMatch(passField, repeatPassField, LOGGER);
            user.setPassword(repeatPassField.getText());
            dao.update(user);
            ProfileController.popOver.hide();
            App.setRoot("profile");

        } catch (InvalidEntryException | IOException e) {
            ViewHelper.failureMessage(failureLabel, e.getMessage());
            refreshPopOver();
        }
    }

    private void refreshPopOver() {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(2000);
                    failureLabel.setVisible(false);
                    repeatPassField.setText("");
                    passField.setText("");
                } catch (InterruptedException ex) {
                    LOGGER.info(ex.getMessage());
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}