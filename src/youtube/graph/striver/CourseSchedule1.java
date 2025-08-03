package youtube.graph.striver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule1 {

    private static boolean isPossible(int N, ArrayList<ArrayList<Integer>> graph) {
        //find indegree for all nodes
        int[] indegree = new int[N];
        for (int i = 0; i < N; i++) {
            for (Integer node: graph.get(i)) {
                indegree[node]++;
            }
        }

        //insert nodes in q that has indegree = 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        //empty q one by one and traverse to all its neighbour to remove the edge b/w them.
        ArrayList<Integer> topoSorted = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer node = q.poll();
            topoSorted.add(node);
            for (Integer adj: graph.get(node)) {
                indegree[adj]--;
                if (indegree[adj] == 0) {
                    q.offer(adj);
                }
            }
        }
        return topoSorted.size() == N;
    }

    private static void createGraph(int N, int[][] prerequisite, ArrayList<ArrayList<Integer>> graph) {
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());
        int len = prerequisite.length;
        for (int i = 0; i < len; i++) {
            graph.get(prerequisite[i][1]).add(prerequisite[i][0]);
        }
    }

    public static void main(String[] args) {
        int N = 4;
        int[][] prerequisiteN = {{1,0},{2,1},{3,2}};
        int M = 2;
        int[][] prerequisiteM = {{0,1},{1,0}};
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        createGraph(N, prerequisiteN, graph);
        System.out.println(isPossible(N, graph));
        graph = new ArrayList<>();
        createGraph(M, prerequisiteM, graph);
        System.out.println(isPossible(M, graph));
    }
}
