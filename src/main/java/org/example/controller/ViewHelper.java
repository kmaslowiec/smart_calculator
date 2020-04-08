package org.example.controller;

import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.exception.InvalidEntryException;
import org.example.utils.MyRegex;
import org.example.utils.MyStrings;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class ViewHelper {

    public static void fieldsCannotBeEmpty(Label infoLabel, List<TextField> fields, Logger LOGGER) throws InvalidEntryException {
        infoLabel.setVisible(true);
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

    public boolean emailValidation(String email, Logger LOGGER) throws InvalidEntryException {
        if (!email.matches(MyRegex.EMAIL_IS_VALID)) {
            LOGGER.info(MyStrings.EMAILS_INVALID_FORMAT);
            throw new InvalidEntryException(MyStrings.EMAILS_INVALID_FORMAT);
        }
        return true;
    }

    public void passwordsDoesNotMatch(PasswordField fresh, PasswordField repeat, Logger LOGGER) throws InvalidEntryException {
        if (!fresh.getText().equals(repeat.getText())) {
            LOGGER.info(MyStrings.PASSWORD_DOES_NOT_MATCH);
            throw new InvalidEntryException(MyStrings.PASSWORD_DOES_NOT_MATCH);
        }
    }
}