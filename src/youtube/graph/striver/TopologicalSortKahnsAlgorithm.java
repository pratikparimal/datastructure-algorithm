package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TopologicalSortKahnsAlgorithm {

    private static Integer[] bfs(int N, ArrayList<ArrayList<Integer>> graph) {
        int[] indegree = new int[N];
        //populate indegree for each node
        for (int i = 0; i < N; i++) {
            for (Integer node: graph.get(i)) {
                indegree[node]++;
            }
        }
        //there will be definitely min of one node that will have indegree 0
        //which means we can start sorting from there
        //insert all the nodes in q that have indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }
        //for each node, go to its neighbour and decrease the indegree i.e. remove the edges b/w them
        //why we are doing this ?
        //because, the curr node should always come before all its neighbour in a sorted seq.
        //so we remove the edge to make sure the node comes before its neighbour
        //once indegree of the node is 0, push the node to q as there is no incoming node left,
        //and now it can be safely placed in the q
        int i = 0;
        Integer[] sorted = new Integer[N];
        while (!q.isEmpty()) {
            Integer node = q.poll();
            sorted[i++] = node;
            for (Integer v: graph.get(node)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        return sorted;
    }

    private static Integer[] topologicalSort(int N, ArrayList<ArrayList<Integer>> graph) {
        Integer[] sorted = bfs(N, graph);
        return sorted;
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
