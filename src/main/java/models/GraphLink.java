package models;

public class GraphLink<T> {
    public GraphNode<T> destNode;
    public int cost;

    public GraphLink(GraphNode<T> destNode, int cost) {
        this.destNode = destNode;
        this.cost = cost;
    }
}
