package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleInDirectedGraphKahnsAlgorithm {

    //we will make use of Kahn's algorithm to do topo sort
    //Kahn's Algo can only be used on DAGs, but we will apply this algo over cyclic graph to check if there is a cycle
    private static boolean bfs(int N, ArrayList<ArrayList<Integer>> graph) {

        //calculate indegree
        int[] indegree = new int[N];
        for (int i = 1; i <= N; i++) {
            for (Integer node: graph.get(i))
                indegree[node]++;
        }

        //insert all the nodes in q with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        ArrayList<Integer> sorted = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            sorted.add(curr);
            for (Integer adj: graph.get(curr)) {
                indegree[adj]--;
                if (indegree[adj] == 0)
                    q.offer(adj);
            }
        }
        //here we can see the sorted array was never completed, it is less than size N
        return sorted.size() < N;
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        graph.get(1).add(2);
        graph.get(2).add(3);
        graph.get(3).add(4);
        graph.get(3).add(5);
        graph.get(4).add(2);
    }

    public static void main(String[] args) {
        int N = 5;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(N);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        boolean isCyclic = bfs(N, graph);
        System.out.println(isCyclic);
    }
}
