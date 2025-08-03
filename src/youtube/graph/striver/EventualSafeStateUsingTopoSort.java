package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class EventualSafeStateUsingTopoSort {
    /**
     * Algo:
     * 1. reverse all the edges of the graph, i.e. given a -> b, convert to a <- b
     * 2. count indegree for all the nodes
     * 3. insert all the nodes in q with indegree 0
     * 4. start traversing q until empty on these inserted nodes
     * 5. remove all the edges b/w adjacent node
     * 6. insert those nodes to q whose indegree becomes 0
     */

    private static ArrayList<Integer> safeNodes(int N, ArrayList<ArrayList<Integer>> graph) {
        ArrayList<ArrayList<Integer>> revGraph = new ArrayList<>();
        for (int i = 0; i < N; i++)
            revGraph.add(new ArrayList<>());
        int[] indegree = new int[N];
        for (int i = 0; i < N; i++) {
            for(Integer j: graph.get(i)) {
                revGraph.get(j).add(i); //step 1
                indegree[i]++; //step 2
            }
        }
        //step 3
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }
        //step 4
        ArrayList<Integer> safeNodes = new ArrayList<>();
        while (!q.isEmpty()) {
            Integer curr = q.poll();
            safeNodes.add(curr);
            for (Integer adj: revGraph.get(curr)) {
                indegree[adj]--; //step 5
                if (indegree[adj] == 0)
                    q.offer(adj); //step 6
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
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
        System.out.println(safeNodes(N, graph));
    }
}
