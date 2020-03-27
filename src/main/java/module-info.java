module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    requires lombok;
    requires ormlite;
    requires static org.mapstruct.processor;

    opens org.example.controller to javafx.fxml;
    opens org.example.entity;
    exports org.example;
    exports org.example.entity;
}