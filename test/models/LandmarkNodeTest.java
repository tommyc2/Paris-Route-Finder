package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class LandmarkNodeTest {

    @Test
    public void testLandmarkNodeCreation() {
        // Create a LandmarkNode with name "Eiffel Tower", coordinates (100, 200), and cultural value 5
        Pixel coords = new Pixel(100, 200);
        LandmarkNode landmarkNode = new LandmarkNode("Eiffel Tower", coords, 5);

        // Test if data is set correctly
        assertEquals("Eiffel Tower", landmarkNode.getName());
        assertEquals(100, landmarkNode.getX());
        assertEquals(200, landmarkNode.getY());
        assertEquals(5, landmarkNode.getCulturalValue());
    }

    @Test
    public void testLandmarkNodeToString() {
        // Create a LandmarkNode with name "Lourve", coordinates (300, 400), and cultural value 10
        Pixel coords = new Pixel(300, 400);
        LandmarkNode landmarkNode = new LandmarkNode("Lourve", coords, 10);

        // Test the toString method
        String expectedToString = "LandmarkNode{x=300, y=400, name='Lourve'}";
        assertEquals(expectedToString, landmarkNode.toString());
    }
}
