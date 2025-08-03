package youtube.graph.striver;

import java.util.ArrayList;

public class CycleInDirectedGraph {

    private static boolean isCyclicDFS(int N, ArrayList<ArrayList<Integer>> graph) {
        boolean[] visited = new boolean[N];
        boolean[] pathVisited = new boolean[N];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                boolean isCycleDetected = dfs(i, graph, visited, pathVisited);
                if (isCycleDetected) return true;
            }
        }
        return false;
    }

    private static boolean dfs(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true;
        pathVisited[node] = true; //this will help to keep track of all the nodes visited in a same path
        for (Integer neighbour: graph.get(node)) {
            if (pathVisited[neighbour])
                return true;
            else if (!visited[neighbour])
                if (dfs(neighbour, graph, visited, pathVisited) == true)
                    return true;
        }
        pathVisited[node] = false;
        return false;
    }

    public static void main(String[] args) {
        int N = 4;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());
        createGraph(graph);
        System.out.println(isCyclicDFS(N, graph));
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(3);
        graph.get(3).add(1);
    }
}
