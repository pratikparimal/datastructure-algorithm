package youtube.graph.apnacollege;

import java.util.ArrayList;

public class Graph {

    static class Edge {
        int src, dest, weight;
        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.weight = w;
        }
    }

    public static void main(String[] args) {
        int v = 4;
        ArrayList<Edge>[] graph = new ArrayList[v];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        createGraph(graph);
        // print 1's neighbour
        System.out.println("Vertex 1's neighbour and weight");
        graph[1].forEach(x -> System.out.println( x.dest + ", " + x.weight));
        System.out.println();
    }

    private static void createGraph(ArrayList<Edge>[] graph) {
        graph[0].add(new Edge(0, 1, 8));
        graph[1].add(new Edge(1, 2, 2));
        graph[1].add(new Edge(1, 3, -1));
        graph[1].add(new Edge(1, 0, 8));
        graph[2].add(new Edge(2, 1, 2));
        graph[2].add(new Edge(2, 3, 10));
        graph[3].add(new Edge(3, 1, -1));
        graph[3].add(new Edge(3, 2, 10));
    }
}
