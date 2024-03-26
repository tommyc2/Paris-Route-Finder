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

    private void testDFS(){
        landmarkNodes.get(0).connectToNodeUndirected(landmarkNodes.get(1),5);
        landmarkNodes.get(1).connectToNodeUndirected(landmarkNodes.get(2),5);
        landmarkNodes.get(2).connectToNodeUndirected(landmarkNodes.get(3),5);

        System.out.println("--- Path Generated-----");
        System.out.println("---------------------------------------");
        List<GraphNode<?>> path= GraphAPI.findPathDepthFirst(landmarkNodes.get(2),null,landmarkNodes.get(0).data);

        if (path != null)
        {
            for(GraphNode<?> node : path)
            {
                System.out.println(node.data);
            }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        //controller.loadData();
        //testDFS();

        /*
        for(GraphNode<LandmarkNode> fromNode : landmarkNodes){
            int x1 = fromNode.data.getX();
            int y1 = fromNode.data.getY();

            for(GraphNode<LandmarkNode> toNode : landmarkNodes){
                if(toNode != fromNode) {
                    int cost = GraphAPI.calculateCostOfEdge(x1, y1, toNode.data.getX(), toNode.data.getY());
                    System.out.println("Cost from " + fromNode.data.getName() + " --> " + toNode.data.getName() + ": " + cost + " pixels");
                }

            }
        }
        */

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