package youtube.graph.apnacollege;

import java.util.ArrayList;

public class CycleDetection {

    static class Edge {
        int src, dest;
        public Edge(int s, int d) {
            this.src= s;
            this.dest = d;
        }
    }

    public static boolean detectCycleUsingDFS(ArrayList<Edge>[] graph, boolean[] visited, int curr, boolean[] recursionStack) {
        visited[curr] = true;
        recursionStack[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (recursionStack[e.dest] == true)
                return true;
            else if (!visited[e.dest])
                detectCycleUsingDFS(graph, visited, e.dest, recursionStack);
        }
        recursionStack[curr] = false;
        return false;
    }

    public static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[2].add(new Edge(2, 3));
        graph[3].add(new Edge(3, 0));
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);
        boolean[] recursionStack = new boolean[V];
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                System.out.println(detectCycleUsingDFS(graph, visited, 0, recursionStack));
        }

    }
}
