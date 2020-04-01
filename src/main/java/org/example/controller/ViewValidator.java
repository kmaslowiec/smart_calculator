package org.example.controller;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.exception.InvalidEntryException;
import org.example.utils.MyStrings;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ViewValidator {

    public static void fieldsCannotBeEmpty(Label failureLabel, List<TextField> fields, Logger LOGGER) throws InvalidEntryException {
        failureLabel.setVisible(true);
        for (TextField i : fields) {
            if (i.getText().trim().isEmpty()) {
                LOGGER.info(MyStrings.FIELDS_CANT_BE_EMPTY);
                throw new InvalidEntryException(MyStrings.FIELDS_CANT_BE_EMPTY);
            }
        }
    }

    public static void failureMessage(Label failureLabel, String msg) {
        failureLabel.setVisible(true);
        failureLabel.setText(msg);
    }

    public static void refreshScene(String fxml, Logger LOGGER) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                try {
                    Thread.sleep(2000);
                    App.setRoot(fxml);
                } catch (InterruptedException | IOException ex) {
                    LOGGER.info(ex.getMessage());
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}