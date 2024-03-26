package utils;

import models.GraphLink;
import models.GraphNode;
import models.LandmarkNode;

import java.util.ArrayList;
import java.util.List;

public class GraphAPI {

    /*  RECURSIVE DEPTH FIRST SEARCH PATH FINDERS */

    public static <T> List<List<GraphNode<?>>> findAllPathsDepthFirst(GraphNode<?> from, List<GraphNode<?>> encountered, T lookingfor){
        List<List<GraphNode<?>>> result=null;
        if(from.data.equals(lookingfor)) {
            List<GraphNode<?>> temp=new ArrayList<>();
            temp.add(from);
            result=new ArrayList<>();
            result.add(temp);
            return result;
        }
        if(encountered == null) encountered = new ArrayList<>();
        encountered.add(from);
        for(GraphLink adjNode : from.adjList){
            if(!encountered.contains(adjNode.destNode)) {
                List<List<GraphNode<?>>> temp2=findAllPathsDepthFirst(adjNode.destNode,new ArrayList<>(encountered),lookingfor); //Use clone of encountered list

                if(temp2!=null) {
                    for(List<GraphNode<?>> x : temp2)
                        x.add(0,from);
                    if(result==null) result=temp2;
                    else result.addAll(temp2);
                }
            }
        }
        return result;
    }


    public static <T> List<GraphNode<?>> findPathDepthFirst(GraphNode<?> from, List<GraphNode<?>> encountered, T lookingfor) {
        List<GraphNode<?>> result;
        if (from.data.equals(lookingfor)) {
            result = new ArrayList<>();
            result.add(from);
            return result;
        }
        if (encountered == null) encountered = new ArrayList<>();
        encountered.add(from);
        for (GraphLink adjNode : from.adjList)
            if (!encountered.contains(adjNode.destNode)) {
                result = findPathDepthFirst(adjNode.destNode, encountered, lookingfor);
                if (result != null) {
                    result.add(0, from);
                    return result;
                }
            }

        return null;
    }

    /*  BREADTH FIRST SEARCH ALGORITHMS */

    /*  DIJKTRA'S ALGORITHM */

    /* GET COST/DISTANCE BETWEEN COORDINATES - landmark nodes */

    public static int calculateCostOfEdge(GraphNode<LandmarkNode> nodeA, GraphNode<LandmarkNode> nodeB){
        /* Reference Material: https://javatutoring.com/distance-between-two-points-java-program/ */
        int x1 = nodeA.data.getX();
        int y1 = nodeA.data.getY();
        int x2 = nodeB.data.getX();
        int y2 = nodeB.data.getY();
        return (int) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

}
