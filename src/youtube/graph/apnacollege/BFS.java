package youtube.graph.apnacollege;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    static class Edge {
        int src, dest;
        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
    }

    private static ArrayList<Integer> bfs(ArrayList<Edge>[] graph, int v) {
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[v];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
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
        ArrayList<Integer> bfs = bfs(graph, v);
        bfs.forEach(x -> System.out.print(x + " "));
    }
}