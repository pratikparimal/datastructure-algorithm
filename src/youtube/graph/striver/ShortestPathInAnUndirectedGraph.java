package youtube.graph.striver;

import java.util.*;

/**
 * You are given an adjacency list,
 * adj of Undirected Graph having unit weight of the edges,
 * find the shortest path from src to all the vertex
 * and if it is unreachable to reach any vertex,
 * then return -1 for that vertex.
 * Input:
 * adj[][] = [[1, 3], [0, 2], [1, 6], [0, 4], [3, 5], [4, 6], [2, 5, 7, 8], [6, 8], [7, 6]]
 * src=0
 * Output: [0, 1, 2, 1, 2, 3, 3, 4, 4]
 */
public class ShortestPathInAnUndirectedGraph {

    private static void bfs(int curr, ArrayList<LinkedHashSet<Integer>> graph,
                            boolean[] visited, int[] distance) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(curr);
        visited[curr] = true;
        while (!queue.isEmpty()) {
            Integer node = queue.poll();
            for(Integer adjNode: graph.get(node)) {
                if (distance[node] + 1 < distance[adjNode]) {
                    distance[adjNode] = distance[node] + 1;
                    queue.offer(adjNode);
                    visited[adjNode] = true;
                }
            }
        }
    }

    private static void createGraph(int N, int[][] adj, ArrayList<LinkedHashSet<Integer>> graph) {
        for (int i = 0; i < N; i++)
            graph.add(new LinkedHashSet<>());
        int row = adj.length;
        for (int i = 0; i < row; i++) {
            int col = adj[i].length;
            for (int j = 0; j < col; j++) {
                graph.get(i).add(adj[i][j]);
                graph.get(adj[i][j]).add(i);
            }
        }
    }

    public int[] shortestPath(ArrayList<LinkedHashSet<Integer>> graph, int src) {
        int N = graph.size();
        int[] distance = new int[N];
        Arrays.fill(distance, 100000);
        distance[src] = 0;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i])
                bfs(i, graph, visited, distance);
        }
        for (int i = 0; i < N; i++) {
            if (distance[i] == 100000)
                distance[i] = -1;
        }
        return distance;
    }

    public static void main(String[] args) {
//        int N = 9;
//        int E = 10;
//        int[][] adj = {{1, 3}, {0, 2}, {1, 6}, {0, 4}, {3, 5}, {4, 6}, {2, 5, 7, 8}, {6, 8}, {7, 6}};
//        int src=0;

        int N = 4;
        int E = 2;
        int[][] adj = {{3}, {3}, {}, {0, 1}};
        int src = 3;
        ArrayList<LinkedHashSet<Integer>> graph = new ArrayList<>();
        createGraph(N, adj, graph);
        int[] shortestPath = new ShortestPathInAnUndirectedGraph().shortestPath(graph, src);
        System.out.println(Arrays.toString(shortestPath));
    }
}
