package Day17fail;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class Day17 {
    /*
     * true - Up/Down
     * false - Right/Left
     */
    private static Integer sinceLastTurn(Node Node)
    {
        if (Node.getShortestPath().size() < 3) return Node.getShortestPath().size();
        Node lastNode = Node.getShortestPath().getLast();
        boolean direction = true;
        Integer distance = 1;
        if(Node.getCordinates()[0] == lastNode.getCordinates()[0]) direction = true;
        else if (Node.getCordinates()[1] == lastNode.getCordinates()[1]) direction = false;
        while(distance < 3)
        {
            if (direction) {
                if (Node.getCordinates()[0] == Node.getShortestPath().get(Node.getShortestPath().size() - 1 - distance).getCordinates()[0]) distance++;
                else return distance;
            }
            else {
                if (Node.getCordinates()[1] == Node.getShortestPath().get(Node.getShortestPath().size() - 1 - distance).getCordinates()[1]) distance++;
                else return distance;
            }
        }
        if(direction) return 3;
        else return 4;
    }
    private static void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
    source.setDistance(0);

    Set<Node> settledNodes = new HashSet<>();
    Set<Node> unsettledNodes = new HashSet<>();

    unsettledNodes.add(source);

    while (unsettledNodes.size() != 0) {
        Node currentNode = getLowestDistanceNode(unsettledNodes);
        unsettledNodes.remove(currentNode);
        for (Map.Entry<Node, Integer> adjacencyPair: currentNode.getAdjacentNodes().entrySet()) {
            Node adjacentNode = adjacencyPair.getKey();
            Integer edgeWeight = adjacencyPair.getValue();
            if (!settledNodes.contains(adjacentNode)) {
                int tmp = sinceLastTurn(currentNode);
                if (tmp < 3) CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                else if (tmp == 3 && currentNode.getCordinates()[0] != adjacentNode.getCordinates()[0]) CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                else if (tmp == 4 && currentNode.getCordinates()[1] != adjacentNode.getCordinates()[1]) CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                unsettledNodes.add(adjacentNode);
            }
        }
        settledNodes.add(currentNode);
    }
    return graph;
    }
    public static Graph CreateGraphFromInput(int[][] input, Node[][] inputNodes){
        Graph graph = new Graph();
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                Node n = inputNodes[i][j];
                Map<Node, Integer> adjacentNodes = new HashMap<Node, Integer>();
                
                if (j != input[i].length - 1) adjacentNodes.put(inputNodes[i][j + 1],input[i][j + 1]);
                if (i != input.length - 1) adjacentNodes.put(inputNodes[i + 1][j],input[i + 1][j]);
                if (j != 0) adjacentNodes.put(inputNodes[i][j - 1],input[i][j - 1]);
                if (i != 0) adjacentNodes.put(inputNodes[i - 1][j],input[i - 1][j]);
                
                n.setAdjacentNodes(adjacentNodes);
                graph.addNode(n);
            }
        }
        return graph;
    }
    public static void main(String[] args) throws Exception {
        List<String> allLines = Files.readAllLines(Paths.get("Day17/test.txt"));

        int[][] input = new int[allLines.size()][allLines.get(0).length()];
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++) {
                input[i][j] = Character.getNumericValue(allLines.get(i).charAt(j));
            }
        }

        Node[][] inputNodes = new Node[allLines.size()][allLines.get(0).length()];
        for (int i = 0; i < allLines.size(); i++) {
            for (int j = 0; j < allLines.get(i).length(); j++) {
                inputNodes[i][j] = new Node(i,j);
            }
        }

        Graph graph = CreateGraphFromInput(input, inputNodes);
        graph = calculateShortestPathFromSource(graph, inputNodes[0][0]);

        System.out.println(inputNodes[inputNodes.length - 1][inputNodes[inputNodes.length - 1].length - 1].getDistance());
    }
    
}
