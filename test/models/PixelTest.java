package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class PixelTest {

    @Test
    public void testPixelCreation() {
        // Create a Pixel with coordinates (100, 200)
        Pixel pixel = new Pixel(100, 200);

        // Test if data is set correctly
        assertEquals(100, pixel.getX());
        assertEquals(200, pixel.getY());
    }

    @Test
    public void testGettersAndSetters() {
        // Create a Pixel with initial values
        Pixel pixel = new Pixel(500, 600);

        // Test the getters and setters
        pixel.setX(1000);
        assertEquals(1000, pixel.getX());

        pixel.setY(1200);
        assertEquals(1200, pixel.getY());
    }
}
