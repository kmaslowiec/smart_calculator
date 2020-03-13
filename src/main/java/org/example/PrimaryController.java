package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class PrimaryController {

    @FXML
    Label calLabel;
    @FXML
    TextField resultField;
    @FXML
    BorderPane mainPane;

    public void initialize() {

    }

    @FXML
    private void cal(){
        String a = resultField.getText();
        String[] b = a.split(" ");
        int res = Integer.parseInt(b[0]) + Integer.parseInt(b[2]);
        resultField.setText(Integer.toString(res));
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
