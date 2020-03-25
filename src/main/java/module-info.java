module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    requires lombok;
    requires static org.mapstruct.processor;

    opens org.example.controller to javafx.fxml;
    exports org.example;
}