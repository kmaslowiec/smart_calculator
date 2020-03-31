package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.App;
import org.example.entity.User;
import org.example.repository.UserDao;
import org.example.utils.MyRegex;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class LoginController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    @FXML
    private Label failureLabel;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passField;

    @FXML
    private Button loginButton;

    @FXML
    private Label successLabel;

    @FXML
    private VBox form;

    @FXML
    private UserDao dao;
    private int attempts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        form.setSpacing(15.0);
        dao = new UserDao();
    }

    public void login(ActionEvent actionEvent) {
        User user;
        String login = loginField.getText();
        if (login.matches(MyRegex.EMAIL_IS_VALID)) {
            user = dao.findByEmail(login);
        } else {
            user = dao.findByName(login);
        }
        boolean isSecured = false;
        if (user != null) {
            isSecured = dao.findById(user.getId()).getPassword().equals(passField.getText());
        }

        if (isSecured) {
            try {
                App.setRoot("calculator");
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        } else {
            attempts++;
            if (attempts == 3) {
                System.exit(0);
            }
            failureLabel.setVisible(true);
            failureLabel.setText("Access denied");
        }
    }
}