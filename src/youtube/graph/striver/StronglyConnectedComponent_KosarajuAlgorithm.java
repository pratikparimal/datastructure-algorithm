package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Stack;

public class StronglyConnectedComponent_KosarajuAlgorithm {

    public static void main(String[] args) {
        int V = 8;
        int[][] edges = {{1},{2},{0,3},{4},{5,7},{6},{4,7},{}};
        int sccCount = kosaraju(V, edges);
        System.out.println("Strongly Connected Component Count: " + sccCount);
    }

    static Stack<Integer> stack = new Stack<>();

    private static int kosaraju(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        createGraph(V, edges, graph);

        //step 1 - store all nodes in a stack according to its finishing time
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                dfs(i, visited, graph);
        }

        //step 2 - reverse the graph
        ArrayList<ArrayList<Integer>> revGraph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            revGraph.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int j: edges[i]) {
                revGraph.get(j).add(i);
            }
        }

        //step 3 - run dfs for each element in stack
        visited = new boolean[V];
        int sccCount = 0;
        ArrayList<ArrayList<Integer>> sccs = new ArrayList<>();
        while (!stack.isEmpty()) {
            ArrayList<Integer> scc = new ArrayList<>();
            int curr = stack.pop();
            if (!visited[curr]) {
                sccCount++;
                scc.add(curr);
                dfs(curr, visited, revGraph);
            } else continue;
            sccs.add(scc);
        }
        System.out.println("Strongly Connected Components : " + sccs);
        return sccCount;
    }

    private static void dfs(int currNode, boolean[] visited,  ArrayList<ArrayList<Integer>> graph) {
        visited[currNode] = true;
        for (int adjNode: graph.get(currNode)) {
            if (!visited[adjNode])
                dfs(adjNode, visited, graph);
        }
        stack.push(currNode);
    }

    private static void createGraph(int V, int[][] edges, ArrayList<ArrayList<Integer>> graph) {
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                graph.get(i).add(edges[i][j]);
            }
        }
    }
}
