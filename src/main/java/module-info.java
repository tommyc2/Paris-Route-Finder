module com.example.parisroutefinder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parisroutefinder to javafx.fxml;
    exports com.example.parisroutefinder;
    exports com.example.parisroutefinder.main;
    opens com.example.parisroutefinder.main to javafx.fxml;
    exports com.example.parisroutefinder.controllers;
    opens com.example.parisroutefinder.controllers to javafx.fxml;
    exports com.example.parisroutefinder.models;
    opens com.example.parisroutefinder.models to javafx.fxml;
    exports com.example.parisroutefinder.utils;
    opens com.example.parisroutefinder.utils to javafx.fxml;
}