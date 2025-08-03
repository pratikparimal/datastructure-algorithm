package youtube.graph.striver;

import java.util.ArrayList;

public class NumberOfProvinces {

    /**
     * input:
     * [{1, 0, 1},
     *  {0, 1, 0},
     *  {1, 0, 1}]
     * output:
     *  no. of provinces
     */
    public static void main(String[] args) {

        int V = 3;
        int[][] provinces = {{1, 0, 1},{0, 1, 0},{1, 0, 1}};
        System.out.println(noOfProvinces(provinces, V));
    }

    private static int noOfProvinces(int[][] provinces, int V) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j && provinces[i][j] > 0){
                    graph.get(i).add(j);
                }
            }
        }
        System.out.println(graph);
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                count++;
                dfs(graph, visited, i);
            }
        }
        return count;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int curr) {
        visited[curr] = true;
        for (Integer i: graph.get(curr)) {
            if (!visited[i])
                dfs(graph, visited, i);
        }
    }
}
