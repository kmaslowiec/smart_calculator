package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.model.SqlConnection;
import org.example.utils.MyStrings;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static final Image ICON = new Image(App.class.getResourceAsStream(MyStrings.ICON_IMG));
    private static Scene scene;

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("calculator"), 500, 700);
        stage.setTitle("Smart Calculator");
        scene.getStylesheets().add
                (App.class.getResource("app.css").toExternalForm());

        stage.getIcons().add(ICON);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        SqlConnection.createDatabase();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}