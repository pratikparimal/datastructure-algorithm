package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TopologicalSort {

    private static Integer[]  topologicalSort(int N, ArrayList<ArrayList<Integer>> graph) {
        boolean[] visited = new boolean[N];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            if (!visited[i])
                dfs(i, graph, visited, stack);
        }
        Integer[] sorted = new Integer[N];
        int i = 0;
        while (!stack.isEmpty()) {
            sorted[i++] = stack.pop();
        }
        return sorted;
    }

    private static void dfs(int currNode, ArrayList<ArrayList<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[currNode] = true;
        for (Integer adjNode: graph.get(currNode)) {
            if (!visited[adjNode])
                dfs(adjNode, graph, visited, stack);
        }
        stack.push(currNode);
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        graph.get(2).add(3);
        graph.get(3).add(1);
        graph.get(4).add(0);
        graph.get(4).add(1);
        graph.get(5).add(0);
        graph.get(5).add(2);
    }

    public static void main(String[] args) {
        int N = 6;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        Integer[] sorted = topologicalSort(N, graph);
        System.out.println(Arrays.deepToString(sorted));
    }
}
