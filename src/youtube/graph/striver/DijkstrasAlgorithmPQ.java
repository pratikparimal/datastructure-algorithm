package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class DijkstrasAlgorithmPQ {

    static class Edge {
        int node, weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int V = 6;
        int S = 0;
        int[][] weightedGraph = {
                {0, 1, 4}, {0, 2, 4},
                {1, 0, 4}, {1, 2, 2},
                {2, 0, 4}, {2, 1, 2}, {2, 3, 3}, {2, 4, 1}, {2, 5, 6},
                {3, 2, 3}, {3, 5, 2},
                {4, 2, 1}, {4, 5, 3},
                {5, 2, 6}, {5, 3, 2}, {5, 4, 3}
        };
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        createGraph(V, weightedGraph, graph);
        int[] distance = shortestPath_DijkstraAlgorithm(V, S, graph);
        System.out.println(Arrays.toString(distance));
    }

    private static void createGraph(int V, int[][] weightedGraph, ArrayList<ArrayList<Edge>> graph) {
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < weightedGraph.length; i++) {
            graph.get(weightedGraph[i][0]).add(new Edge(weightedGraph[i][1], weightedGraph[i][2]));
        }
    }

    private static int[] shortestPath_DijkstraAlgorithm(int V, int S, ArrayList<ArrayList<Edge>> graph) {
        int[] distance = new int[V];
        Arrays.fill(distance, 1000000);
        distance[S] = 0;
        //here the comparator used is on the weight first then on node
        PriorityQueue<Edge> queue = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight)
                return a.weight - b.weight;
            else return a.node - b.node;
        });
        queue.offer(new Edge(S, 0));
        while (!queue.isEmpty()) {
            Edge curr = queue.poll();
            int currNode = curr.node;
            int currWt = curr.weight;
            for (Edge adj: graph.get(currNode)) {
                int adjNode = adj.node;
                int adjWt = adj.weight;
                if (currWt + adjWt < distance[adjNode]) {
                    distance[adjNode] = currWt + adjWt;
                    queue.offer(new Edge(adjNode, currWt + adjWt));
                }
            }
        }
        for (int i = 0; i < V; i++) {
            if (distance[i] == 1000000)
                distance[i] = -1;
        }
        return distance;
    }
}
