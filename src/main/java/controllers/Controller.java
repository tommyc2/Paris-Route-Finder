package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import main.Driver;
import utils.Utilities;

import java.io.File;

public class Controller {

    public ImageView imageView = new ImageView();

    @FXML
    public void loadImage() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(Driver.primaryStage);
        if(file != null){
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            System.out.println("Image Dimensions --> " + Utilities.getImageDimensions(imageView.getImage()));
        }
    }
}