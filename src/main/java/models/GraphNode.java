package models;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<T> {
    public T data;
    public List<GraphLink<T>> adjList = new ArrayList<>();

    public GraphNode(T data) {
        this.data = data;
    }

    public void connectToNodeDirected(GraphNode<T> destNode, int cost) {
        adjList.add(new GraphLink<>(destNode, cost));
    }

    public void connectToNodeUndirected(GraphNode<T> destNode, int cost) {
        adjList.add(new GraphLink<>(destNode, cost));
        destNode.adjList.add(new GraphLink<>(this, cost));
    }
}

