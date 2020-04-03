package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.example.App;
import org.example.model.UserDao;
import org.example.service.CalculatorService;
import org.example.service.impl.CalculatorServiceImpl;
import org.example.utils.InMemory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    @FXML
    private Label hello;

    @FXML
    TextField resultField;

    @FXML
    BorderPane mainPane;

    @FXML
    Button one;

    @FXML
    Button two;

    @FXML
    Button three;

    @FXML
    Button four;

    @FXML
    Button five;

    @FXML
    Button six;

    @FXML
    Button seven;

    @FXML
    Button eight;

    @FXML
    Button nine;

    @FXML
    Button plus;

    @FXML
    Button minus;

    @FXML
    Button equal;

    @FXML
    Button back;

    @FXML
    Button clear;

    @FXML
    Button send;

    private CalculatorService calculator = new CalculatorServiceImpl();
    private InMemory memory;
    private UserDao dao = new UserDao();
    private String input;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memory = new InMemory();
        hello.setText("Hello " + memory.getUser().getName());
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
}