package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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

    public List<Integer> imageArray = new ArrayList<>();
    public Text titleText;
    public Text startText;
    public Text destinationText;
    public Text generateShortestRouteText;
    public Text generateMostHistoricalRouteText;

    @FXML
    private ChoiceBox<String> startChoiceBox;
    @FXML
    private ChoiceBox<String> endChoiceBox;

    public void initialize() throws IOException {
        loadData(); // Ensure this is called before trying to populate the ComboBoxes
        populateChoiceBoxes();
        plotPointsWithLabels();
        addLandmarkLinks();

        // Testing DFS Lines on map
       //List<GraphNode<?>> path = GraphAPI.findPathDepthFirst(landmarkNodes.get(0),null,landmarkNodes.get(7).data);
        //drawLinesBetweenLandmarkNodes(path);
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
    public void processBitmap() throws FileNotFoundException {
        Image bitmap = new Image(new FileInputStream("src/main/resources/com/example/parisroutefinder/bitmap-paris.bmp"));
        Image image = imageView.getImage();
        PixelReader pixelReader = bitmap.getPixelReader();
        WritableImage writableImage = new WritableImage((int) bitmap.getWidth(), (int) bitmap.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        //int[] imgArrray = new int[(int) image.getWidth() * (int) image.getHeight()];

        for (int ycoord = 0; ycoord < bitmap.getHeight(); ycoord++) {

            for (int xcoord = 0; xcoord < bitmap.getWidth(); xcoord++) {
                Color colorOfPixel = pixelReader.getColor(xcoord, ycoord);

                if ((!colorOfPixel.equals(Color.BLACK)) && (!colorOfPixel.equals(Color.WHITE))) {
                    pixelWriter.setColor(xcoord, ycoord, Color.WHITE);
                    imageArray.add(0);
                }

            }
        }
       // System.out.println(imageArray.toString());
        System.out.println("Number of pixels in image: " + imageArray.size());
        //imageView.setImage(writableImage);
    }

    public boolean areSimilar(Color color, Color color1){
        boolean blue = (Math.abs(color.getBlue() - color1.getBlue()) <= 0.1);
        boolean green = (Math.abs(color.getGreen() - color1.getGreen()) <= 0.1);
        boolean red = (Math.abs(color.getRed() - color1.getRed()) <= 0.1);
        boolean hue = (Math.abs(color.getHue() - color1.getHue()) < 0.1);
        boolean sat = (Math.abs(color.getSaturation() - color1.getSaturation()) < 0.1);
        boolean brightness = (Math.abs(color.getBrightness() - color1.getBrightness()) < 0.1);
        // return (red && blue && green);
        return sat && brightness && hue && green && red && blue;
    }

    public void resetMap(){
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        anchorPane.getChildren().removeIf(component -> component instanceof Circle);
        anchorPane.getChildren().removeIf(component -> component instanceof Line);
    }

/* loading in data from csv */
    public void loadData() throws IOException {
        /* Source: https://www.baeldung.com/java-csv-file-array */
        String row = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/parisroutefinder/nodes.csv"));
            while ((row = reader.readLine()) != null) {
                String[] line = row.split(",");
                LandmarkNode lmn = new LandmarkNode(line[0],new Pixel(Integer.parseInt(line[1]),Integer.parseInt(line[2])));
                GraphNode<LandmarkNode> gnode = new GraphNode<>(lmn);
                this.landmarkNodes.add(gnode);
                //System.out.println("P: " + lmn.getName() + ", X: "+lmn.getX() + ", Y: "+ lmn.getY());
            }
            System.out.println("Number of nodes: " + landmarkNodes.size());
        }
        catch(IOException error){
            System.out.println("Error occured when loading data: "+error);
        }
    }

    public void plotPointsWithLabels(){

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
    }

    @FXML
    private void handleGenerateShortestRoute(ActionEvent event) {
        String startLandmarkName = startChoiceBox.getValue();
        String endLandmarkName = endChoiceBox.getValue();

        // Code to initiate shortest path calculation...
    }

    @FXML
    private void handleGenerateCulturalRoute(ActionEvent event) {
        String startLandmarkName = startChoiceBox.getValue();
        String endLandmarkName = endChoiceBox.getValue();

        // Code to initiate cultural route calculation...
    }

    @Provisional
    public void drawLinesBetweenLandmarkNodes(List<GraphNode<?>> pathList){
        for (int i = 0; i < pathList.size()-1; i++) {
                GraphNode<LandmarkNode> nodeA = (GraphNode<LandmarkNode>) pathList.get(i);
                GraphNode<LandmarkNode> nodeB = (GraphNode<LandmarkNode>) pathList.get(i+1);
                Line line = new Line(nodeA.data.getX(),nodeA.data.getY(),nodeB.data.getX(),nodeB.data.getY());
                line.setStroke(Color.RED);
                line.setStrokeWidth(5);

                line.setLayoutY(imageView.getLayoutY());
                line.setLayoutX(imageView.getLayoutX());
                AnchorPane ap = (AnchorPane) imageView.getParent();
                ap.getChildren().add(line);

        }
    }

    @Provisional
    public void generateDepthFSRoute(ActionEvent actionEvent) {
        resetMap();

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

        if (startNode != null && destNode != null) {
            List<GraphNode<?>> pathList = GraphAPI.findPathDepthFirst(startNode,null,destNode.data);
            drawLinesBetweenLandmarkNodes(pathList);
        }

    }


}