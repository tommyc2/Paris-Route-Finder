module com.example.parisroutefinder {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    exports main;
    opens main to javafx.fxml;
    exports controllers;
    opens controllers to javafx.fxml;
    exports models;
    opens models to javafx.fxml;
    exports utils;
    opens utils to javafx.fxml;



}
