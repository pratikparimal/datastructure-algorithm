package youtube.graph.apnacollege;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_DisconnectedGraph {

    static class Edge {
        int src, dest;
        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    private static ArrayList<Integer> bfs(ArrayList<Edge>[] graph, int v, boolean[] visited, int start) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            if (!visited[curr]) {
                result.add(curr);
                visited[curr] = true;
                for (int j = 0; j < graph[curr].size(); j++) {
                    q.offer(graph[curr].get(j).dest);
                }
            }
        }
        return result;
    }

     // There are some scenario where graph has disconnected component
     // In this case there are multiple starting point of a graph
    private static void createDisconnectedGraph(ArrayList<Edge>[] graph) {
        /*
            1 -- 3
          /      |
        0        |
          \      |
            2 -- 4

            5 -- 6
         */
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[5].add(new Edge(5, 6));
        graph[6].add(new Edge(6, 5));
    }

    /**
     * To traverse a disconnected component,
     * we create a visited array in the main method to keep track of visited vertices
     * We start with 0, bfs() keep adding/removing from queue for the single graph
     * For first unvisited vertex, i.e. false, we take this vertex as starting point again and call bfs()
     */
    public static void main(String[] args) {
        int v = 7;
        ArrayList<Edge>[] graph = new ArrayList[v];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        createDisconnectedGraph(graph);
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                ArrayList<Integer> bfs = bfs(graph, v, visited, i);
                bfs.forEach(x -> System.out.print(x + " "));
            }
        }
    }
}
