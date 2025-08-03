package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Given a Directed Acyclic Graph of V vertices from 0 to n-1
 * and a 2D Integer array(or vector) edges[][] of length E,
 * where there is a directed edge from
 * edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.
 * Find the shortest path from src(0) vertex to all the vertices
 * and if it is impossible to reach any vertex,
 * then return -1 for that vertex.
 *
 * Input: V = 6, E = 7,
 * edges = [[0,1,2], [0,4,1], [4,5,4], [4,2,2], [1,2,3], [2,3,6], [5,3,1]]
 * Output: [0, 2, 3, 6, 1, 5]
 */

/**
 * Solution:
 * 1. Do toposort to get the sorted array, anyway bfs or dfs
 *    Using bfs here to get the stack of nodes
 * 2. Create distance[V] array with MAX value, also update the starting nodes distance as 0
 * 3. Pop out nodes from stack and traverse to all adj nodes
 * 4. Update distance[adj] with minimum of [sum(distance[curr] + adj.dis), distance[adj]]
 * 5. return distance[] as result, this should have the shortest path to each node from node(0)
 */
public class ShortestPathInDAG_Toposort {

    static class Path {
        int dest;
        int weight;
        public Path(int dest, int weight) {
            this.weight = weight;
            this.dest = dest;
        }

        @Override
        public String toString() {
            return "{" + dest + "," + weight + '}';
        }
    }

    private static void dfs(int curr, ArrayList<ArrayList<Path>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[curr] = true;
        for (Path adj: graph.get(curr)) {
            if (!visited[adj.dest])
                dfs(adj.dest, graph, visited, stack);
        }
        stack.push(curr);
    }

    private static Stack<Integer> toposort_dfs(int V, ArrayList<ArrayList<Path>> graph) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, stack);
            }
        }
        return stack;
    }

    private static void createGraph(int V, int E, int[][] edges, ArrayList<ArrayList<Path>> graph) {
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < E; i++)
            graph.get(edges[i][0]).add(new Path(edges[i][1], edges[i][2]));
    }

    public int[] shortestPath(int V, int E, int[][] edges) {
        ArrayList<ArrayList<Path>> graph = new ArrayList<>();
        createGraph(V, E, edges, graph);
        int[] distance = new int[V];
        Arrays.fill(distance, 100000);
        distance[0] = 0;
        Stack<Integer> stack =  toposort_dfs(V, graph);
        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (Path adj : graph.get(curr)) {
                int dis = adj.weight;
                int dest = adj.dest;
                distance[dest] = Math.min(dis + distance[curr], distance[dest]);
            }
        }
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == 100000)
                distance[i] = -1;
        }
        return distance;
    }

    public static void main(String[] args) {
//        int V = 6, E = 7;
//        int[][] edges = {{0,1,2},{0,4,1},{4,5,4},{4,2,2},{1,2,3},{2,3,6},{5,3,1}};
        int V = 10, E = 24;
        int[][] edges = {
                {0,2,6},{0,3,7},{0,4,9},{0,6,8},{0,7,6},
                {1,2,6},{1,3,7},{1,5,10},{1,6,1},{1,7,4},
                {2,3,3},{2,6,10},{2,8,8},{2,9,10},
                {3,5,3},{3,6,10},{3,7,5},
                {5,6,9},{5,7,7},
                {6,7,7},{6,8,8},{6,9,8},
                {7,9,1},
                {8,9,6}};
        ShortestPathInDAG_Toposort sp = new ShortestPathInDAG_Toposort();
        int[] shortestPath = sp.shortestPath(V, E, edges);
        System.out.println(Arrays.toString(shortestPath));
    }
}