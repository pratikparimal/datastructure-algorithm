package youtube.graph.apnacollege;

import java.util.ArrayList;

public class DFS {
    static class Edge {
        int src, dest;
        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    static ArrayList<Integer> traversedList = new ArrayList<>();

    private static void dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited) {
        traversedList.add(curr);
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest])
                dfs(graph, e.dest, visited);
        }
    }

    private static void createGraph(ArrayList<Edge>[] graph) {
        /*
            1 -- 3
          /      | \
        0        |   5 -- 6
          \      | /
            2 -- 4
         */
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));
        graph[6].add(new Edge(6, 5));
    }

    public static void main(String[] args) {
        int v = 7;
        ArrayList<Edge>[] graph = new ArrayList[v];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        createGraph(graph);
        boolean[] visited = new boolean[v];
        dfs(graph, 0, visited);
        traversedList.forEach(x -> System.out.print(x + " "));
    }
}
