package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Bipartite Graph :-
 * Any graph that can be coloured with two different colour such that no adjacent nodes have same colour
 */
public class BipartiteGraph {

    private static boolean isBipartiteGraphDFS(int N, ArrayList<ArrayList<Integer>> graph) {
        int[] coloured = new int[N + 1];
        Arrays.fill(coloured, -1);
        for (int i = 1; i <= N; i++) {
            if (coloured[i] == -1) {
                if(!dfs(i, -1, graph, coloured))
                    return false;
            }
        }
        return true;
    }

    private static boolean dfs(int curr, int parent, ArrayList<ArrayList<Integer>> graph, int[] coloured) {
        coloured[curr] = parent == -1 ? 1 : (coloured[parent] == 0) ? 1 : 0;
        for(Integer i: graph.get(curr)) {
            if (coloured[i] == -1) {
                dfs(i, curr, graph, coloured);
            } else if (coloured[curr] == coloured[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBipartiteGraphBFS(int N, ArrayList<ArrayList<Integer>> graph) {
        int[] coloured = new int[N + 1];
        Arrays.fill(coloured, -1);
        for (int i = 1; i <= N; i++) {
            if (coloured[i] == -1) {
                if(!bfs(i, graph, coloured))
                    return false;
            }
        }
        return true;
    }

    private static boolean bfs(int startNode, ArrayList<ArrayList<Integer>> graph, int[] coloured) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(startNode);
        coloured[startNode] = 1;
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            for (Integer i: graph.get(curr)) {
                if (coloured[i] == -1) {
                    q.offer(i);
                    coloured[i] = (coloured[curr] == 0) ? 1 : 0;
                } else if (coloured[curr] == coloured[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
//        graph.get(1).add(2);
//        graph.get(2).add(1);
//        graph.get(2).add(3);
//        graph.get(2).add(6);
//        graph.get(3).add(2);
//        graph.get(3).add(4);
//        graph.get(4).add(3);
//        graph.get(4).add(5);
//        graph.get(4).add(7);
//        graph.get(5).add(4);
//        graph.get(5).add(6);
//        graph.get(6).add(2);
//        graph.get(6).add(5);
//        graph.get(7).add(4);
//        graph.get(7).add(8);
//        graph.get(8).add(7);

        graph.get(1).add(2);
        graph.get(2).add(1);
        graph.get(2).add(3);
        graph.get(2).add(6);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(4).add(3);
        graph.get(4).add(7);
        graph.get(5).add(6);
        graph.get(5).add(7);
        graph.get(6).add(2);
        graph.get(6).add(5);
        graph.get(7).add(4);
        graph.get(7).add(5);
        graph.get(7).add(8);
        graph.get(8).add(7);
    }

    public static void main(String[] args) {
        int N = 8;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        createGraph(graph);
        System.out.println(isBipartiteGraphBFS(N, graph));
        System.out.println(isBipartiteGraphDFS(N, graph));
    }

}
