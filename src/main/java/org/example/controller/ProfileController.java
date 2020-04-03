package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import org.example.App;
import org.example.entity.User;
import org.example.model.UserDao;
import org.example.model.model_utils.BlobHelper;
import org.example.utils.InMemory;
import org.example.utils.MyStyles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Circle avatarsPicture;

    @FXML
    private AnchorPane node;

    @FXML
    private TextField email;

    @FXML
    private TextField login;

    @FXML
    private HBox buttonsBox;

    @FXML
    private VBox infoBox;

    private User user;
    private UserDao dao;
    private BlobHelper blob;
    private InMemory memory;
    private MyStyles styles;
    private File file;
    private double avatarsRadius = 75.0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonsBox.setSpacing(15.0);
        infoBox.setSpacing(15.0);
        memory = new InMemory();
        user = memory.getUser();
        dao = new UserDao();
        blob = new BlobHelper();
        styles = new MyStyles();
        fillTheFields();
    }

    private void fillTheFields() {
        login.setText(user.getName());
        email.setText(user.getEmail());
        blob.showAvatar(avatarsPicture, memory, avatarsRadius);
    }

    @FXML
    private void updateFile(MouseEvent mouseEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            InputStream imgStream = new FileInputStream(file);
            styles.transformToCircle(avatarsPicture, new Image(imgStream), avatarsRadius);
        }
    }

    @FXML
    private void saveProfile(ActionEvent actionEvent) throws IOException {
        user.setName(login.getText());
        user.setEmail(email.getText());
        if (file != null) {
            byte[] pic = BlobHelper.fileToBytes(file);
            user.setPicture(pic);
        }
        dao.update(user);
        App.setRoot("calculator");
    }

    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {
        App.setRoot("calculator");
    }
}