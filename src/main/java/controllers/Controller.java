package controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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

    public List<Integer> imageArray = new ArrayList<>();

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
                else if(colorOfPixel.equals(Color.WHITE)){
                    pixelWriter.setColor(xcoord, ycoord, Color.WHITE);
                    imageArray.add(0);
                }
                else{
                    pixelWriter.setColor(xcoord,ycoord,Color.BLACK);
                    imageArray.add(1);
                }

            }
        }
        System.out.println(imageArray.toString());
        imageView.setImage(writableImage);
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
               // Testing -->  System.out.println("P: " + lmn.getName() + ", X: "+lmn.getX() + ", Y: "+ lmn.getY());
            }
        }
        catch(IOException error){
            System.out.println(error);
        }
    }

    public void resetMap(){
        AnchorPane anchorPane = (AnchorPane) imageView.getParent();
        anchorPane.getChildren().removeIf(component -> component instanceof Rectangle);
        anchorPane.getChildren().removeIf(component -> component instanceof Label);
        imageView.setEffect(null);
    }
}