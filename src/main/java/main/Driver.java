package main;
import controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;
import utils.GraphAPI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Driver extends Application {

    private Controller controller = new Controller();

    private List<GraphNode<LandmarkNode>> landmarkNodes = controller.landmarkNodes;

    @Override
    public void start(Stage stage) throws IOException {

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