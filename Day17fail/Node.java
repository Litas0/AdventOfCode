package Day17fail;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {
    
    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    private int[] cordinates = new int[2];

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(int i, int j)
    {
        cordinates[0] = i;
        cordinates[1] = j;
    }
    
    public Integer getDistance() { return distance; }
    public void setDistance(Integer distance) { this.distance = distance; }

    public List<Node> getShortestPath() { return shortestPath; }
    public void setShortestPath(List<Node> shortestPath) { this.shortestPath = shortestPath; }

    public Map<Node, Integer> getAdjacentNodes() { return adjacentNodes; }
    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) { this.adjacentNodes = adjacentNodes; }

    public int[] getCordinates() { return cordinates; }
    public void setCordinates(int[] coordinates) { this.cordinates = coordinates; }
}