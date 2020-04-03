package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.example.App;
import org.example.entity.User;
import org.example.model.UserDao;
import org.example.model.model_utils.BlobHelper;
import org.example.utils.InMemory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ImageView profilePic;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonsBox.setSpacing(15.0);
        infoBox.setSpacing(15.0);
        InMemory memory = new InMemory();
        user = memory.getUser();
        dao = new UserDao();
        fillTheFields();
    }

    private void fillTheFields() {
        login.setText(user.getName());
        email.setText(user.getEmail());
        if (user.getPicture() != null) {
            profilePic.setImage(BlobHelper.bytesToImage(user.getPicture()));
        }
    }

    @FXML
    private void updateFile(MouseEvent mouseEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {
            InputStream imgStream = new FileInputStream(file);
            profilePic.setImage(new Image(imgStream));
            byte[] pic = BlobHelper.fileToBytes(file);
            user.setPicture(pic);
        }
    }

    @FXML
    private void saveProfile(ActionEvent actionEvent) {
        user.setName(login.getText());
        user.setEmail(email.getText());
        dao.update(user);
    }

    @FXML
    private void cancel(ActionEvent actionEvent) throws IOException {
        App.setRoot("calculator");
    }
}