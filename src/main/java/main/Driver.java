package main;

import controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {

    Controller controller = new Controller();
    @Override
    public void start(Stage stage) throws IOException {
        controller.loadData();
        FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource("/com/example/parisroutefinder/parisroutefindergui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 850);
        stage.setTitle("City of Paris Route Finder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}