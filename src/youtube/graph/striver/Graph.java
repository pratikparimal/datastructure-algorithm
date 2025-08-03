package youtube.graph.striver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int startNode) {
        ArrayList<Integer> traversedList = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(startNode);
        visited[startNode] = true;
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            traversedList.add(curr);
            for (Integer i: graph.get(curr)) {
                if (!visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
        return traversedList;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int startNode, ArrayList<Integer> traversedList) {
        traversedList.add(startNode);
        visited[startNode] = true;
        for (Integer i : graph.get(startNode)) {
            if (!visited[i])
                dfs(graph, visited, i, traversedList);
        }
    }

    public static void main(String[] args) {
        int N = 10;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                System.out.println("BFS: " +  bfs(graph, visited, i));
            }
        }
        ArrayList<Integer> traversedList = null;
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                traversedList = new ArrayList<>();
                dfs(graph, visited, i, traversedList);
                System.out.println("DFS: " + traversedList);
            }
        }
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        graph.get(1).add(2);
        graph.get(1).add(6);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(4);
        graph.get(3).add(2);
        graph.get(4).add(2);
        graph.get(4).add(5);
        graph.get(5).add(4);
        graph.get(5).add(8);
        graph.get(6).add(1);
        graph.get(6).add(7);
        //graph.get(6).add(9);
        graph.get(7).add(6);
        graph.get(7).add(8);
        graph.get(8).add(5);
        graph.get(8).add(7);
        //graph.get(9).add(6);
        graph.get(9).add(10);
        graph.get(10).add(9);
    }
}
