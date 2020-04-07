module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    requires lombok;
    requires ormlite;
    requires static org.mapstruct.processor;
    requires java.desktop;
    requires org.controlsfx.controls;

    opens org.example.controller to javafx.fxml;
    opens org.example.entity;
    exports org.example;
    exports org.example.entity;
}