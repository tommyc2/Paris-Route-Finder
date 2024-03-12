module com.example.parisroutefinder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parisroutefinder to javafx.fxml;
    exports com.example.parisroutefinder;
}