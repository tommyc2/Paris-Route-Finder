package models;

import org.junit.Test;
import static org.junit.Assert.*;

public class GraphNodeTest {

    @Test
    public void testGraphNodeCreation() {
        // Create a GraphNode with data "A"
        GraphNode<String> nodeA = new GraphNode<>("A");

        // Test if data is set correctly
        assertEquals("A", nodeA.data);
    }

    @Test
    public void testConnectToNodeUndirected() {
        // Create two GraphNodes with data "A" and "B"
        GraphNode<String> nodeA = new GraphNode<>("A");
        GraphNode<String> nodeB = new GraphNode<>("B");

        // Connect nodeA to nodeB with a cost of 10
        nodeA.connectToNodeUndirected(nodeB, 10);

        // Test if nodeA and nodeB are correctly connected
        assertEquals(1, nodeA.adjList.size());
        assertEquals(nodeB, nodeA.adjList.get(0).destNode);
        assertEquals(10, nodeA.adjList.get(0).cost);

        assertEquals(1, nodeB.adjList.size());
        assertEquals(nodeA, nodeB.adjList.get(0).destNode);
        assertEquals(10, nodeB.adjList.get(0).cost);
    }
}
