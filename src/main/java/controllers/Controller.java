package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import main.Driver;
import models.GraphNode;
import models.LandmarkNode;
import models.Pixel;
import utils.Utilities;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.io.File;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    public ImageView imageView = new ImageView();

    List<GraphNode<LandmarkNode>> landmarkNodes = new LinkedList<>();


    public void blackAndWhiteConversion() {
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        anchorPane.getChildren().removeIf(component -> component instanceof Rectangle);
        anchorPane.getChildren().removeIf(component -> component instanceof Line);
        anchorPane.getChildren().removeIf(component -> component instanceof Label);
        anchorPane.getChildren().removeIf(component -> component instanceof Text);
        anchorPane.getChildren().removeIf(component -> component instanceof Circle);

        Image image = imageView.getImage();
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writableImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        for (int ycoord = 0; ycoord < image.getHeight(); ycoord++) {

            for (int xcoord = 0; xcoord < image.getWidth(); xcoord++) {
                Color colorOfPixel = pixelReader.getColor(xcoord, ycoord);

                if (colorOfPixel.equals(Color.WHITE)) {
                    pixelWriter.setColor(xcoord, ycoord, Color.WHITE);
                } else {
                    pixelWriter.setColor(xcoord, ycoord, Color.BLACK);
                }

            }

        }
        imageView.setImage(writableImage);
    }

    public void loadData() throws IOException {
        /* Source: https://www.baeldung.com/java-csv-file-array */
        String row = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/parisroutefinder/nodes.csv"));
            while ((row = reader.readLine()) != null) {
                String[] line = row.split(",");
                LandmarkNode lmn = new LandmarkNode(line[0],new Pixel(Integer.parseInt(line[1]),Integer.parseInt(line[2])));
                GraphNode<LandmarkNode> gnode = new GraphNode<>(lmn);
                landmarkNodes.add(gnode);
                System.out.println("P: " + lmn.getName() + ", X: "+lmn.getX() + ", Y: "+ lmn.getY());
            }
        }
        catch(IOException error){
            System.out.println(error);
        }
    }
}