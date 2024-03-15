package models;

public class GraphLink {

    // Source node can be stored too if needs be
    public GraphNode<?> destNode;
    public int cost;
    public GraphLink(GraphNode<?> destNode, int cost) {
        this.destNode=destNode;
        this.cost=cost;
    }
}
