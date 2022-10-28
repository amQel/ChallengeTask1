package com.challenge.models.app;

import java.util.Comparator;

public class Node implements Comparator<Node> {
    public int id;
    public int cost;

    public Node() {
    } // empty constructor

    public Node(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
