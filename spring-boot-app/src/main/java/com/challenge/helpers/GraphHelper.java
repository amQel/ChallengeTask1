package com.challenge.helpers;

import com.challenge.models.app.Node;
import java.util.*;

public class GraphHelper {
    private Node[] allNodes;
    private int dist[];
    private int prev[];
    private PriorityQueue<Node> priorityQueue;
    private int graphSize;
    private List<List<Node>> _graph;

    public GraphHelper(int _graphSize) {
        this.graphSize = _graphSize;
        dist = new int[_graphSize];
        prev = new int[_graphSize];
        priorityQueue = new PriorityQueue<Node>(_graphSize, new Node());
        allNodes = new Node[_graphSize];
    }

    public List<Node> dijkstra(List<List<Node>> graph, int origin, int destination) {
        this._graph = graph;

        if (origin < 0 || destination < 0 || _graph.get(origin).size() == 0 || _graph.get(destination).size() == 0) {
            return new ArrayList<Node>();
        }

        initGraph(origin);

        while (priorityQueue.size() > 0) {
            Node topNode = priorityQueue.remove();
            if (topNode.id == destination) {
                return getResult(origin, destination);
            }
            for (int i = 0; i < _graph.get(topNode.id).size(); i++) {
                Node v = _graph.get(topNode.id).get(i);
                int distance = dist[topNode.id] + v.cost;
                if (distance < dist[v.id]) {
                    dist[v.id] = distance;
                    prev[v.id] = topNode.id;
                    updateNodeDistanceInPriorityQueue(v.id, distance);
                }
            }
        }
        return new ArrayList<Node>();

    }

    private List<Node> getResult(int origin, int destination) {
        int currentNode = destination;
        List<Node> result = new ArrayList<Node>();

        result.add(allNodes[currentNode]);
        while (prev[currentNode] != -1) {
            currentNode = prev[currentNode];
            result.add(0, allNodes[currentNode]);
        }

        return result;
    }

    private void initGraph(int origin) {
        dist[origin] = 0;

        for (int i = 0; i < graphSize; i++) {
            if (i != origin) {
                dist[i] = Integer.MAX_VALUE;
            }
            prev[i] = -1;
            Node newNode = new Node(i, dist[i]);
            allNodes[i] = newNode;
        }

        priorityQueue.add(allNodes[origin]);
    }

    private void updateNodeDistanceInPriorityQueue(int nodeId, int newDistance) {
        Node oldNode = allNodes[nodeId];
        priorityQueue.remove(oldNode);

        Node newNode = new Node(nodeId, newDistance);
        allNodes[nodeId] = newNode;
        priorityQueue.add(newNode);
    }

}
