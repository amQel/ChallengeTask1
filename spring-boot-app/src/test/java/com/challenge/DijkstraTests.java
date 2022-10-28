package com.challenge;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import com.challenge.helpers.GraphHelper;
import com.challenge.models.app.Node;

public class DijkstraTests {
    private static final int TEST_GRAPH_SIZE = 5;
    private List<List<Node>> graph;
    private List<Node> path;

    @Before
    public void initGraph() {
        GraphHelper graphHelper = new GraphHelper(TEST_GRAPH_SIZE);

        graph = new ArrayList<>();
        for (int i = 0; i < TEST_GRAPH_SIZE; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new Node(1, 1));
        graph.get(0).add(new Node(2, 1));
        graph.get(0).add(new Node(3, 1));
        graph.get(1).add(new Node(0, 1));
        graph.get(1).add(new Node(2, 1));
        graph.get(2).add(new Node(0, 1));
        graph.get(2).add(new Node(1, 1));
        graph.get(3).add(new Node(0, 1));
        graph.get(3).add(new Node(4, 1));
        graph.get(4).add(new Node(3, 1));

        path = graphHelper.dijkstra(graph, 1, 4);
    }

    @Test
    public void pathShouldExist() {
        assertTrue(path != null);
    }

    @Test
    public void pathLenghtShouldBeCorrect() {
        assertTrue(path.size() == 4);
    }

    @Test
    public void pathShouldBeCorrect() {
        assertArrayEquals(Stream.of(1, 0, 3, 4).toArray(), path.stream().map(n -> n.id).toArray());
    }

    @Test
    public void distanceShouldBeCorrect() {
        assertEquals(path.get(path.size() - 1).cost, 3);
    }
}
