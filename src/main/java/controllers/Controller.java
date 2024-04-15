package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import models.GraphLink;
import models.GraphNode;
import models.LandmarkNode;
import models.Pixel;
import utils.GraphAPI;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import java.io.*;
import java.util.*;

import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;


public class Controller {

    public ImageView imageView = new ImageView();
    public List<GraphNode<LandmarkNode>> landmarkNodes = new ArrayList<>();
    public ListView<List<GraphNode<?>>> listViewDepthFS = new ListView<>();
    public TextField maxNumDepthFSRoutes = new TextField();
    public ListView<GraphNode<?>> waypointsListView = new ListView<>();
    public ChoiceBox<String> avoidPointChoiceBox;
    @FXML
    private ChoiceBox<String> startChoiceBox;
    @FXML
    private ChoiceBox<String> endChoiceBox;
    
    private List<GraphNode<LandmarkNode>> startAndEndNodesBreadthFS = new ArrayList<>();

    private List<GraphNode<LandmarkNode>> pixelPoints = new ArrayList<>(); // List of road points (white) for BFS

    public void initialize() throws IOException {
        loadData(); // Ensure this is called before trying to populate the ComboBoxes
        populateChoiceBoxes();
        plotPointsWithLabels();
        addLandmarkLinks();
        processBitmap();

        //System.out.println("------------------------------------------");
       // List<List<GraphNode<?>>> allPaths = GraphAPI.findAllPathsDepthFirst(landmarkNodes.get(0), null, landmarkNodes.get(3).data);
      //  int pathCount = 1;
      //  int maxNumOfPaths = 10;

        //for (List<GraphNode<?>> path : allPaths) {
         //   if (pathCount <= 10) {
        //        System.out.println("\nPath " + (pathCount++) + "\n--------");
         //       for (GraphNode<?> node : path) {
         //           System.out.println(node.data);
         //       }
         //   }
       // }


    }

    @Provisional
    public void addLandmarkLinks(){

        ///////////////////////////////////////////
        /* Provisional Until better method found */
        ///////////////////////////////////////////

        GraphNode<LandmarkNode> eiffelTower = landmarkNodes.get(0);
        GraphNode<LandmarkNode> louvre = landmarkNodes.get(1);
        GraphNode<LandmarkNode> notredame = landmarkNodes.get(2);
        GraphNode<LandmarkNode> arcdetriomphe = landmarkNodes.get(3);
        GraphNode<LandmarkNode> sacrecoeur = landmarkNodes.get(4);
        GraphNode<LandmarkNode> montmatre = landmarkNodes.get(5);
        GraphNode<LandmarkNode> musee = landmarkNodes.get(6);
        GraphNode<LandmarkNode> palais = landmarkNodes.get(7);
        GraphNode<LandmarkNode> champselysees = landmarkNodes.get(8);
        GraphNode<LandmarkNode> concorde = landmarkNodes.get(9);
        GraphNode<LandmarkNode> pantheon = landmarkNodes.get(10);
        GraphNode<LandmarkNode> lux = landmarkNodes.get(11);
        GraphNode<LandmarkNode> pompidou = landmarkNodes.get(12);
        GraphNode<LandmarkNode> saintechapelle = landmarkNodes.get(13);
        GraphNode<LandmarkNode> pontalexandre = landmarkNodes.get(14);
        GraphNode<LandmarkNode> vendome = landmarkNodes.get(15);
        GraphNode<LandmarkNode> a = landmarkNodes.get(16);
        GraphNode<LandmarkNode> b = landmarkNodes.get(17);
        GraphNode<LandmarkNode> c = landmarkNodes.get(18);
        GraphNode<LandmarkNode> d = landmarkNodes.get(19);

        eiffelTower.connectToNodeUndirected(musee,GraphAPI.calculateCostOfEdge(musee,eiffelTower));
        concorde.connectToNodeUndirected(vendome,GraphAPI.calculateCostOfEdge(vendome,concorde));
        notredame.connectToNodeUndirected(pompidou,GraphAPI.calculateCostOfEdge(pompidou,notredame));
        a.connectToNodeUndirected(musee,GraphAPI.calculateCostOfEdge(a,musee));
        a.connectToNodeUndirected(louvre,GraphAPI.calculateCostOfEdge(a,louvre));
        pontalexandre.connectToNodeUndirected(eiffelTower,GraphAPI.calculateCostOfEdge(pontalexandre,eiffelTower));
        eiffelTower.connectToNodeUndirected(arcdetriomphe, GraphAPI.calculateCostOfEdge(eiffelTower,arcdetriomphe));
        eiffelTower.connectToNodeUndirected(a,GraphAPI.calculateCostOfEdge(eiffelTower,a));
        arcdetriomphe.connectToNodeUndirected(champselysees, GraphAPI.calculateCostOfEdge(arcdetriomphe,champselysees));
        champselysees.connectToNodeUndirected(pontalexandre, GraphAPI.calculateCostOfEdge(champselysees,pontalexandre));
        pontalexandre.connectToNodeUndirected(concorde, GraphAPI.calculateCostOfEdge(pontalexandre,concorde));
        concorde.connectToNodeUndirected(musee, GraphAPI.calculateCostOfEdge(concorde,musee));
        musee.connectToNodeUndirected(vendome, GraphAPI.calculateCostOfEdge(musee,vendome));
        vendome.connectToNodeUndirected(louvre, GraphAPI.calculateCostOfEdge(vendome,louvre));
        louvre.connectToNodeUndirected(pompidou, GraphAPI.calculateCostOfEdge(louvre,pompidou));
        louvre.connectToNodeUndirected(saintechapelle, GraphAPI.calculateCostOfEdge(louvre,saintechapelle));
        saintechapelle.connectToNodeUndirected(pompidou, GraphAPI.calculateCostOfEdge(saintechapelle,pompidou));
        saintechapelle.connectToNodeUndirected(notredame, GraphAPI.calculateCostOfEdge(saintechapelle,notredame));
        notredame.connectToNodeUndirected(lux, GraphAPI.calculateCostOfEdge(notredame,lux));
        lux.connectToNodeUndirected(pantheon, GraphAPI.calculateCostOfEdge(lux,pantheon));
        notredame.connectToNodeUndirected(pantheon, GraphAPI.calculateCostOfEdge(notredame,pantheon));
        lux.connectToNodeUndirected(a, GraphAPI.calculateCostOfEdge(lux,a));
        a.connectToNodeUndirected(c, GraphAPI.calculateCostOfEdge(a,c));
        pontalexandre.connectToNodeUndirected(musee,GraphAPI.calculateCostOfEdge(pontalexandre,musee));
        c.connectToNodeUndirected(d, GraphAPI.calculateCostOfEdge(c,d));
        d.connectToNodeUndirected(pantheon, GraphAPI.calculateCostOfEdge(d,pantheon));
        b.connectToNodeUndirected(d, GraphAPI.calculateCostOfEdge(b,d));
        b.connectToNodeUndirected(pantheon, GraphAPI.calculateCostOfEdge(b,pantheon));
        palais.connectToNodeUndirected(sacrecoeur, GraphAPI.calculateCostOfEdge(palais,sacrecoeur));
        sacrecoeur.connectToNodeUndirected(montmatre, GraphAPI.calculateCostOfEdge(sacrecoeur,montmatre));
        palais.connectToNodeUndirected(champselysees, GraphAPI.calculateCostOfEdge(palais,champselysees));
        palais.connectToNodeUndirected(concorde, GraphAPI.calculateCostOfEdge(palais,concorde));
        palais.connectToNodeUndirected(pompidou, GraphAPI.calculateCostOfEdge(palais,pompidou));

    }


    public List<GraphNode<?>> findNearbyRoadPoints(GraphNode<?> nodeA){
        List<GraphNode<?>> nearbyNodes = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        for(GraphNode<?> nodeB : this.pixelPoints){
            if (nodeB != nodeA) {
                distances.add(GraphAPI.calculateCostOfEdge((GraphNode<LandmarkNode>) nodeB, (GraphNode<LandmarkNode>) nodeA));
            }
        }
        Collections.sort(distances);

        GraphNode<?> adjacentNode1 = null;
        GraphNode<?> adjacentNode2 = null;
        GraphNode<?> adjacentNode3 = null;

        for(GraphNode<?> node : this.pixelPoints){
            int d = GraphAPI.calculateCostOfEdge((GraphNode<LandmarkNode>) node, (GraphNode<LandmarkNode>) nodeA);
            if ((distances.get(0) == d) && adjacentNode1 == null && d != 0) {
                adjacentNode1 = node;
            }
            if ((distances.get(1) == d) && adjacentNode2 == null && d != 0) {
                adjacentNode2 = node;
            }
            if ((distances.get(2) == d) && adjacentNode3 == null && d != 0) {
                adjacentNode3 = node;
            }
        }



        nearbyNodes.add(adjacentNode1);
        nearbyNodes.add(adjacentNode2);
        nearbyNodes.add(adjacentNode3);

        return nearbyNodes;
    }


    // Returns 2-3 close/nearby nodes for the purpose of incorporating waypoint into LandmarkNodes list etc
    public List<GraphNode<?>> findNearbyLandmarkNodes(GraphNode<?> nodeA){
        List<GraphNode<?>> nearbyNodes = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        for(GraphNode<?> nodeB : this.landmarkNodes){
            if (nodeB != nodeA) {
                distances.add(GraphAPI.calculateCostOfEdge((GraphNode<LandmarkNode>) nodeB, (GraphNode<LandmarkNode>) nodeA));
            }
        }
        Collections.sort(distances);

        GraphNode<?> adjacentNode1 = null;
        GraphNode<?> adjacentNode2 = null;
        GraphNode<?> adjacentNode3 = null;

        for(GraphNode<?> node : this.landmarkNodes){
            int d = GraphAPI.calculateCostOfEdge((GraphNode<LandmarkNode>) node, (GraphNode<LandmarkNode>) nodeA);
            if ((distances.get(0) == d) && adjacentNode1 == null && d != 0) {
                adjacentNode1 = node;
            }
            if ((distances.get(1) == d) && adjacentNode2 == null && d != 0) {
                adjacentNode2 = node;
            }
            if ((distances.get(2) == d) && adjacentNode3 == null && d != 0) {
                adjacentNode3 = node;
            }
        }



        nearbyNodes.add(adjacentNode1);
        nearbyNodes.add(adjacentNode2);
        nearbyNodes.add(adjacentNode3);

        return nearbyNodes;
    }

    //TODO
    public void processBitmap() throws FileNotFoundException {
        Image bitmap = new Image(new FileInputStream("src/main/resources/com/example/parisroutefinder/bitmap-paris.bmp"));
        Image image = imageView.getImage();
        PixelReader pixelReader = bitmap.getPixelReader();
        WritableImage writableImage = new WritableImage((int) bitmap.getWidth(), (int) bitmap.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int ycoord = 0; ycoord < bitmap.getHeight(); ycoord++) {

            for (int xcoord = 0; xcoord < bitmap.getWidth(); xcoord++) {
                Color colorOfPixel = pixelReader.getColor(xcoord, ycoord);

                if ((!colorOfPixel.equals(Color.BLACK))) {
                    pixelWriter.setColor(xcoord, ycoord, Color.WHITE);
                    // add new road pixel node
                    pixelPoints.add(new GraphNode<>(new LandmarkNode("RoadPixel",new Pixel(xcoord,ycoord),3)));
                }
                else{
                    pixelWriter.setColor(xcoord, ycoord, Color.BLACK);
                    // add black pixels too for ease of use with indexes
                    pixelPoints.add(new GraphNode<>(new LandmarkNode("B",new Pixel(xcoord,ycoord),3)));
                }

            }
        }

        for (int y = 0; y < imageView.getImage().getHeight(); y++)
        {
            for (int x = 0; x < imageView.getImage().getWidth(); x++)
            {
                int indexOfPixel = y * (int) imageView.getImage().getWidth() + x;

                if(pixelPoints.get(indexOfPixel).data.getName().equals("RoadPixel")) {

                    int rightIndex = indexOfPixel+1;
                    int belowIndex = indexOfPixel+((int)image.getWidth());

                    // If pixel to the right is a white pixel, link up with it
                    if((rightIndex < pixelPoints.size()) && (pixelPoints.get(rightIndex).data.getName().equals("RoadPixel"))){
                        pixelPoints.get(indexOfPixel).connectToNodeUndirected(pixelPoints.get(rightIndex),GraphAPI.calculateCostOfEdge(pixelPoints.get(indexOfPixel),pixelPoints.get(rightIndex)));
                    }
                    // If pixel below is white, link up with it
                    if((belowIndex < pixelPoints.size()) && (pixelPoints.get(belowIndex).data.getName().equals("RoadPixel"))){
                        pixelPoints.get(indexOfPixel).connectToNodeUndirected(pixelPoints.get(belowIndex),GraphAPI.calculateCostOfEdge(pixelPoints.get(indexOfPixel),pixelPoints.get(belowIndex)));
                    }



                }

            }
        }

        System.out.println("Number of pixel points in image: " + (pixelPoints.size()));
        //imageView.setImage(writableImage);

    }

    public void resetMap() {
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        anchorPane.getChildren().removeIf(component -> component instanceof Line);
        anchorPane.getChildren().removeIf(component -> component instanceof Circle);
        landmarkNodes.clear(); // Reset landmark node list and re-add them so that all temporary waypoints added are removed
        listViewDepthFS.getItems().clear();
        waypointsListView.getItems().clear();
        maxNumDepthFSRoutes.clear();
        startAndEndNodesBreadthFS.clear();

        try {
            loadData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Connect each node again as the list was reset
        addLandmarkLinks();

    }

/* loading in data from csv */
    public void loadData() throws IOException {
        /* Source: https://www.baeldung.com/java-csv-file-array */
        String row = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/parisroutefinder/nodes.csv"));
            while ((row = reader.readLine()) != null) {
                String[] line = row.split(",");
                LandmarkNode lmn = new LandmarkNode(line[0],new Pixel(Integer.parseInt(line[1]),Integer.parseInt(line[2])),Integer.parseInt(line[3]));
                GraphNode<LandmarkNode> gnode = new GraphNode<>(lmn);
                this.landmarkNodes.add(gnode);
                //System.out.println("P: " + lmn.getName() + ", X: "+lmn.getX() + ", Y: "+ lmn.getY());
            }
            System.out.println("Successfully loaded data in");
            System.out.println("Total number of nodes:" + landmarkNodes.size());
        }
        catch(IOException error){
            System.out.println("Error occured when loading data: "+error);
        }
    }

    public void plotPointsWithLabels(){

        /* - Loop through each landmark
           - Display landmark as rectangular point
         */
        for(GraphNode<LandmarkNode> node : this.landmarkNodes){
            Rectangle rect = new Rectangle(node.data.getX(),node.data.getY(),7,7);
            rect.setFill(Color.BLACK);
            rect.setLayoutY(imageView.getLayoutY());
            rect.setLayoutX(imageView.getLayoutX());
            Text label = new Text();
            label.setText(node.data.getName());
            label.setLayoutX(imageView.getLayoutX());
            label.setLayoutY(imageView.getLayoutY());
            label.setX(rect.getX()-5);
            label.setY(rect.getY()-2);
            AnchorPane ap = (AnchorPane) imageView.getParent();
            ap.getChildren().add(rect);
            ap.getChildren().add(label);
        }
    }

    private void populateChoiceBoxes() {
        ObservableList<String> landmarkNames = FXCollections.observableArrayList();
        for (GraphNode<LandmarkNode> node : landmarkNodes) {
            landmarkNames.add(node.data.getName());
        }

        startChoiceBox.setItems(landmarkNames);
        endChoiceBox.setItems(landmarkNames);
        avoidPointChoiceBox.setItems(landmarkNames);
    }

    public void drawLinesBetweenLandmarkNodes(MouseEvent event){
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        anchorPane.getChildren().removeIf(component -> component instanceof Line);

        List<GraphNode<?>> pathList;
            if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                pathList = listViewDepthFS.getSelectionModel().getSelectedItem();
            //   System.out.println("Total distance/cost (pixel units): " + GraphAPI.calculateTotalDistanceOfPath(pathList));

                for (int i = 0; i < pathList.size()-1; i++) {
                    GraphNode<LandmarkNode> nodeA = (GraphNode<LandmarkNode>) pathList.get(i);
                    GraphNode<LandmarkNode> nodeB = (GraphNode<LandmarkNode>) pathList.get(i+1);
                    Line line = new Line(nodeA.data.getX(),nodeA.data.getY(),nodeB.data.getX(),nodeB.data.getY());
                    line.setStroke(Color.RED);
                    line.setStrokeWidth(4);
                    line.setLayoutY(imageView.getLayoutY());
                    line.setLayoutX(imageView.getLayoutX());
                    AnchorPane ap = (AnchorPane) imageView.getParent();
                    ap.getChildren().add(line);

                }
            }
    }

    public void generateDepthFSRoutes(ActionEvent actionEvent) {
        listViewDepthFS.getItems().clear();

        String startingNodeName = startChoiceBox.getValue();
        String destNodeName = endChoiceBox.getValue();
        GraphNode<LandmarkNode> startNode = null;
        GraphNode<LandmarkNode> destNode = null;

        for(GraphNode<LandmarkNode> node : landmarkNodes){
            if(node.data.getName().equals(startingNodeName)) {
                startNode = node;
            }
            if(node.data.getName().equals(destNodeName)) {
                destNode = node;
            }
        }

        List<List<GraphNode<?>>> listOfPaths = GraphAPI.findAllPathsDepthFirst(startNode,null,destNode.data);

        // Limiting paths generated by user-specified value
        int pathCount = 1;
        for(List<GraphNode<?>> path : listOfPaths){
            if(pathCount <= getMaxNumRoutesDepthFS()){
                listViewDepthFS.getItems().add(path); // Adding each path as a separate entry in ListView for Depth First Search routes generated
                pathCount++;
            }
       }

    }

    private int getMaxNumRoutesDepthFS() {
        if(maxNumDepthFSRoutes.getText().isEmpty()) return 1; else return Integer.parseInt(maxNumDepthFSRoutes.getText());
    }

    @FXML
    private void findShortestPathDijkstra(ActionEvent event) {
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        anchorPane.getChildren().removeIf(component -> component instanceof Line);

        String startLandmarkName = startChoiceBox.getValue();
        String endLandmarkName = endChoiceBox.getValue();

        GraphNode<LandmarkNode> startNode = findNodeByName(startLandmarkName);
        GraphNode<LandmarkNode> endNode = findNodeByName(endLandmarkName);

        List<GraphNode<LandmarkNode>> shortestPath = GraphAPI.findCheapestPathDijkstra(startNode, endNode, landmarkNodes);

        List<GraphNode<LandmarkNode>> waypoints = new ArrayList<>();
        List<List<GraphNode<LandmarkNode>>> waypointPaths = new ArrayList<>();

        if (!waypointsListView.getItems().isEmpty()){
         for(GraphNode<LandmarkNode> node : landmarkNodes){
             if(node.data.getName().equals("WayPoint")){
                 waypoints.add(node);
             }
         }
         System.out.println("Waypoints added to their own list temporarily\n");
         System.out.println(waypoints.size());

         waypointPaths.add(GraphAPI.findCheapestPathDijkstra(startNode, waypoints.get(0),landmarkNodes));

         for(int j = 0; j < waypoints.size()-1; j++){
             waypointPaths.add(GraphAPI.findCheapestPathDijkstra(waypoints.get(j),waypoints.get(j+1),landmarkNodes));
         }
         waypointPaths.add(GraphAPI.findCheapestPathDijkstra(waypoints.get(waypoints.size()-1),endNode,landmarkNodes));

         for(List<GraphNode<LandmarkNode>> list : waypointPaths){
             visualizePathOnMap(list,Color.MEDIUMPURPLE);
         }

        }
        else{
            visualizePathOnMap(shortestPath, Color.RED);
        }

    }

    public void generateShortestPathBreadthFS(ActionEvent event){
           List<GraphNode<LandmarkNode>> path = GraphAPI.findShortestPathBFS(startAndEndNodesBreadthFS.get(0),startAndEndNodesBreadthFS.get(1));
           visualizePathOnMap(path,Color.RED);
    }

    @FXML
    public void findShortestPathBFS(MouseEvent event) {
        if (startAndEndNodesBreadthFS.size() < 3) {
            Circle marker = new Circle(event.getX(), event.getY(), 3);
            marker.setLayoutX(imageView.getLayoutX());
            marker.setLayoutY(imageView.getLayoutY());
            marker.setFill(Color.GREY);
            marker.setStrokeWidth(3.5);
            marker.setStroke(Color.DARKBLUE);
            marker.setCenterX(event.getX());
            marker.setCenterY(event.getY());
            AnchorPane drawingArea = (AnchorPane) imageView.getParent();
            drawingArea.getChildren().add(marker);

            Pixel pixel = new Pixel((int) event.getX(), (int) event.getY());
            GraphNode<LandmarkNode> newNode = new GraphNode<>(new LandmarkNode("RoadPixel", pixel, 3));

            pixelPoints.add(newNode);
            startAndEndNodesBreadthFS.add(newNode);

            List<GraphNode<?>> listOfNearbyRoadNodes = findNearbyRoadPoints(newNode);

            System.out.println("Number of nearby nodes: " + listOfNearbyRoadNodes.size() + "\n");

            System.out.print(listOfNearbyRoadNodes);

            for (GraphNode<?> nearbyNode : listOfNearbyRoadNodes) {
                if (nearbyNode != null) newNode.connectToNodeUndirected((GraphNode<LandmarkNode>) nearbyNode, GraphAPI.calculateCostOfEdge(newNode, (GraphNode<LandmarkNode>) nearbyNode));
            }
            boolean isRemoved = pixelPoints.remove(newNode);
            if(isRemoved) pixelPoints.add(newNode);

        }
    }



    private GraphNode<LandmarkNode> findNodeByName(String name) {
        for (GraphNode<LandmarkNode> node : landmarkNodes) {
            if (node.data.getName().equalsIgnoreCase(name)) {
                return node;
            }
        }
        return null; // Node not found
    }

    private void visualizePathOnMap(List<GraphNode<LandmarkNode>> path, Color color) {
        if (path == null || path.isEmpty()) {
            System.out.println("Path is empty or null");
            return;
        }

        double imageViewX = imageView.getLayoutX();
        double imageViewY = imageView.getLayoutY();

        for (int i = 0; i < path.size() - 1; i++) {
            GraphNode<LandmarkNode> currentNode = path.get(i);
            GraphNode<LandmarkNode> nextNode = path.get(i + 1);

            // Adjust line start and end positions based on the imageView's layout properties
            Line line = new Line(
                    imageViewX + currentNode.data.getX(),
                    imageViewY + currentNode.data.getY(),
                    imageViewX + nextNode.data.getX(),
                    imageViewY + nextNode.data.getY()
            );
            line.setStroke(color); // Set line color
            line.setStrokeWidth(2); // Set line width

            AnchorPane drawingArea = (AnchorPane) imageView.getParent();
            drawingArea.getChildren().add(line); // Add the line to the AnchorPane
        }
    }

    @Provisional
    public void addWayPoint(MouseEvent event) {
        if(event.getClickCount() == 1) {
            findShortestPathBFS(event);
        }

        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2){
            Circle dot = new Circle(event.getX(), event.getY(),4);
            dot.setLayoutX(imageView.getLayoutX());
            dot.setLayoutY(imageView.getLayoutY());
            dot.setFill(Color.WHITE);
            dot.setStrokeWidth(3.5);
            dot.setStroke(Color.PURPLE);
            dot.setCenterX(event.getX());
            dot.setCenterY(event.getY());
            AnchorPane drawingArea = (AnchorPane) imageView.getParent();
            drawingArea.getChildren().add(dot); // Add the circle to the AnchorPane

            // Code to add dot to landmarkNodes list (waypoint)
            Pixel pixel = new Pixel((int)event.getX(),(int)event.getY());

            // Determine cultural significance of waypoint
            // Major cultural significance range: x = 221-521, y = 174-396
            int histValue = 0;
            if(pixel.getX() > 221 && pixel.getX() <= 521 && pixel.getY() > 174 && pixel.getY() <= 396){
                histValue = 55;
            }
            else{
                histValue = 3;
            }

            LandmarkNode newNode = new LandmarkNode("WayPoint",pixel,histValue);
            GraphNode<LandmarkNode> waypointNode = new GraphNode<>(newNode);
            waypointsListView.getItems().add(waypointNode);

            landmarkNodes.add(waypointNode);

            List<GraphNode<?>> nearbyNodes = findNearbyLandmarkNodes(waypointNode);
            for(GraphNode<?> node : nearbyNodes){
                waypointNode.connectToNodeUndirected((GraphNode<LandmarkNode>) node,GraphAPI.calculateCostOfEdge(waypointNode,(GraphNode<LandmarkNode>) node));
            }
            boolean waypointRemoved = landmarkNodes.remove(waypointNode);
            if (waypointRemoved) landmarkNodes.add(waypointNode);

        }

    }

    public void avoidPointInPaths(ActionEvent actionEvent) {
        GraphNode<LandmarkNode> nodeToAvoid = findNodeByName(avoidPointChoiceBox.getValue());
        
        for(GraphNode<LandmarkNode> lmnode : landmarkNodes){
            for(int j = 0; j < lmnode.adjList.size()-1; j++){
                if(lmnode.adjList.get(j).destNode.data.getName().equals(nodeToAvoid != null ? nodeToAvoid.data.getName() : null)){
                    boolean removedPoint = lmnode.adjList.remove(lmnode.adjList.get(j));
                    if (removedPoint) System.out.println("REmoved");
                }
            }
        }
        landmarkNodes.remove(nodeToAvoid);
    }

    public void generateMostHistoricalRoute(ActionEvent actionEvent) {
        String startNodeName = startChoiceBox.getValue();
        String endNodeName = endChoiceBox.getValue();

        GraphNode<LandmarkNode> startNode = findNodeByName(startNodeName);
        GraphNode<LandmarkNode> destNode = findNodeByName(endNodeName);

        List<GraphNode<LandmarkNode>> mostHistoricalPath = GraphAPI.dijkstrasHistoricalPath(startNode,destNode,landmarkNodes);

        System.out.println("----- Most Historical Path ---------");
        for(GraphNode<LandmarkNode> pathNode : mostHistoricalPath){
            System.out.println(pathNode.data.getName()+ ": " + pathNode.data.getCulturalValue());
        }
            System.out.println("----- ----------------- ---------");

    }


    public void displayLinksWithLines(ActionEvent actionEvent) {
       for(GraphNode<LandmarkNode> node : landmarkNodes){
           if (!node.adjList.isEmpty()){
               for(GraphLink<LandmarkNode> graphNodeLink : node.adjList){
                   GraphNode<LandmarkNode> destNode = graphNodeLink.destNode;

                   List<GraphNode<LandmarkNode>> miniPath = new ArrayList<>();
                   miniPath.add(node);
                   miniPath.add(destNode);
                   visualizePathOnMap(miniPath,Color.BLUE);
               }
           }
       }
    }
}