package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given:
 * A directed graph
 * Vertices V
 * Edges E
 * Nodes 0 to V-1
 *
 * Terminology:
 * Terminal Nodes -> any node ia a terminal node, if it has no outgoing edges
 * Safe Nodes -> a node is safe node, if every possible path starting from that node
 * leads to a terminal node.
 *
 * Output:
 * Return an array containing all the safe nodes in ascending order
 */
public class EventualSafeState {

    private static boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph,
                               boolean[] visited, boolean[] pathVisited, boolean[] safeNode) {
        visited[curr] = true;
        pathVisited[curr] = true;
        safeNode[curr] = false;
        for (int neighbour: graph.get(curr)) {
            if (pathVisited[neighbour])
                return true;
            else if (!visited[neighbour])
                if (dfs(neighbour, graph, visited, pathVisited, safeNode))
                    return true;
        }
        safeNode[curr] = true;
        pathVisited[curr] = false;
        return false;
    }

    private static Integer[] isCycleDetected(int N, ArrayList<ArrayList<Integer>> graph) {
        boolean[] visited = new boolean[N];
        boolean[] pathVisited = new boolean[N];
        boolean[] safeNode = new boolean[N];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited, pathVisited, safeNode);
            }
        }
        ArrayList<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (safeNode[i])
                safeNodes.add(i);
        }
        return safeNodes.toArray(new Integer[0]);
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(5);
        graph.get(3).add(0);
        graph.get(4).add(5);
        graph.get(7).add(8);
        graph.get(9).add(1);
    }

    public static void main(String[] args) {
        int N = 10;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());
        createGraph(graph);
        Integer[] cycleDetected = isCycleDetected(N, graph);
        System.out.println(Arrays.toString(cycleDetected));
    }
}
