package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class ShortestPathInUndirectWeightedUsingDijkstras {

    static class Edge {
        int node, weight;
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public String toString() {
            return String.format("{%d,%d}", this.node, this.weight);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 6;
        int start = 1;
        int[][] edges = {{1,2,2},{1,4,1},{2,5,5},{2,3,4},{3,5,1},{4,3,3}};
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        createGraph(V, edges, graph);
        ArrayList<Integer> shortestPath = shortestPath(V, start, graph);
        System.out.println(shortestPath);
    }

    private static ArrayList<Integer> shortestPath(int V, int start, ArrayList<ArrayList<Edge>> graph) {
        ArrayList<Integer> resultantPath = new ArrayList<>();
        //A parent array to keep track of previous node from where curr node have come
        int[] parent = new int[V + 1];
        for (int i = 0; i <= V; i++)
            parent[i] = i;
        // distance array to keep track of distance of each node from starting node
        int[] distance = new int[V + 1];
        Arrays.fill(distance, (int) 1e9);
        distance[start] = 0;
        //a min heap queue to insert node
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> {
            if (a.weight != b.weight) return a.weight - b.weight;
            else return a.node - b.node;
        });
        pq.offer(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            for (Edge adj: graph.get(curr.node)) {
                if (curr.weight + adj.weight < distance[adj.node]) {
                    distance[adj.node] = curr.weight + adj.weight;
                    pq.offer(new Edge(adj.node, distance[adj.node]));
                    parent[adj.node] = curr.node; //keep updating parent node for all adj node
                }
            }
        }
        //start traversing from last and backtrack to the starting node to get the path
        int node = V;
        while (parent[node] != node) {
            resultantPath.add(node);
            node = parent[node];
        }
        resultantPath.add(start);
        Collections.reverse(resultantPath);
        return resultantPath;
    }

    private static void createGraph(int V, int[][] edges, ArrayList<ArrayList<Edge>> graph) {
        for (int i = 0; i <= V; i++)
            graph.add(new ArrayList<>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            graph.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
    }
}
