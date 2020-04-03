package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.example.App;
import org.example.model.model_utils.BlobHelper;
import org.example.service.CalculatorService;
import org.example.service.impl.CalculatorServiceImpl;
import org.example.utils.InMemory;
import org.example.utils.MyStyles;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    @FXML
    private Circle avatarCircle;

    @FXML
    private VBox avatarVBox;

    @FXML
    private Label hello;

    @FXML
    private TextField resultField;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button plus;

    @FXML
    private Button minus;

    @FXML
    private Button equal;

    @FXML
    private Button back;

    @FXML
    private Button clear;

    @FXML
    private Button send;

    private CalculatorService calculator;
    private InMemory memory;
    private MyStyles styles;
    private BlobHelper blob;
    private String input;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        avatarVBox.setSpacing(5.0);
        calculator = new CalculatorServiceImpl();
        memory = new InMemory();
        styles = new MyStyles();
        blob = new BlobHelper();
        initAvatarBox();
    }

    @FXML
    private void cal() {
        input = resultField.getText();
        if (!input.isEmpty()) {
            resultField.setText(calculator.calculate(input));
        }
    }

    @FXML
    private void logOut() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void goToMyProfile() throws IOException {
        App.setRoot("profile");
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        if (event.getSource() == one) {
            resultField.appendText("1");
        } else if (event.getSource() == two) {
            resultField.appendText("2");
        } else if (event.getSource() == three) {
            resultField.appendText("3");
        } else if (event.getSource() == four) {
            resultField.appendText("4");
        } else if (event.getSource() == five) {
            resultField.appendText("5");
        } else if (event.getSource() == six) {
            resultField.appendText("6");
        } else if (event.getSource() == seven) {
            resultField.appendText("7");
        } else if (event.getSource() == eight) {
            resultField.appendText("8");
        } else if (event.getSource() == nine) {
            resultField.appendText("9");
        } else if (event.getSource() == plus) {
            resultField.appendText("+");
        } else if (event.getSource() == minus) {
            resultField.appendText("-");
        } else if (event.getSource() == equal) {
            resultField.appendText("=");
        } else if (event.getSource() == back) {
            if (resultField.getLength() > 0) {
                String a = resultField.getText().substring(0, resultField.getLength() - 1);
                int i = resultField.getCaretPosition() - 1;
                resultField.setText(a);
                resultField.positionCaret(i);
            }
        } else if (event.getSource() == clear) {
            resultField.setText("");
        } else if (event.getSource() == send) {
            input = resultField.getText();
            if (!input.isEmpty()) {
                resultField.setText(calculator.calculate(input));
            }
        }
    }

    private void initAvatarBox() {
        hello.setText("Hi " + memory.getUser().getName());
        blob.showAvatar(avatarCircle, memory, 40.0);
    }
}