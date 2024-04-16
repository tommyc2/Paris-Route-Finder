package models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class GraphLinkTest {
    @Test
    public void testGraphLinkCreation() {
        // Create a GraphNode for destination
        GraphNode<String> destNode = new GraphNode<>("Destination");

        // Create a GraphLink with cost 10
        GraphLink<String> graphLink = new GraphLink<>(destNode, 10);

        // Test if destNode and cost are set correctly
        assertEquals(destNode, graphLink.destNode);
        assertEquals(10, graphLink.cost);
    }


}