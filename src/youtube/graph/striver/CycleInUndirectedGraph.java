package youtube.graph.striver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CycleInUndirectedGraph {

    static class Edge {
        int parent, node;
        public Edge(int p, int n) {
            this.parent = p;
            this.node = n;
        }
    }

    private static boolean detectCycleUsingDFS(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int currNode, int parent) {
        visited[currNode] = true;
        for (Integer neighbour: graph.get(currNode)) {
            if (!visited[neighbour]) {
                if(detectCycleUsingDFS(graph, visited, neighbour, currNode))
                    return true;
            } else if (neighbour != parent) {
                return true;
            }
        }
        return false;
    }

    private static boolean detectCycleUsingBFS(ArrayList<ArrayList<Integer>> graph, boolean[] visited, int startNode) {
        Queue<Edge> q = new LinkedList<>();
        q.offer(new Edge(-1, startNode));
        visited[startNode] = true;
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            int node = curr.node;
            int parent = curr.parent;
            for(Integer neighbour: graph.get(node)) {
                if (!visited[neighbour]) {
                    q.offer(new Edge(node, neighbour));
                    visited[neighbour] = true;
                } else if (parent != neighbour) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isCyclicBFS(int V, ArrayList<ArrayList<Integer>> graph) {
        boolean[] visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                if(detectCycleUsingBFS(graph, visited, i))
                    return true;
            }
        }
        return false;
    }

    private static boolean isCyclicDFS(int V, ArrayList<ArrayList<Integer>> graph) {
        boolean[] visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            if (!visited[i]) {
                if(detectCycleUsingDFS(graph, visited, i, -1))
                    return true;
            }
        }
        return false;
    }

    private static void createGraph(ArrayList<ArrayList<Integer>> graph) {
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(1);
        graph.get(2).add(5);
        graph.get(3).add(1);
        graph.get(3).add(4);
        graph.get(3).add(6);
        graph.get(4).add(3);
        graph.get(5).add(2);
        graph.get(5).add(7);
        graph.get(6).add(3);
        graph.get(6).add(7);
        graph.get(7).add(5);
        graph.get(7).add(6);
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        createGraph(graph);
        System.out.println(isCyclicBFS(V, graph));
        System.out.println(isCyclicDFS(V, graph));
    }
}
