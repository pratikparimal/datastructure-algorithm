package youtube.graph.striver;

import java.util.*;

public class MST_PrimsAlgorithm {

    static class Edge {
        int weight, node, parent;
        public Edge(int weight, int node) {
            this.weight = weight;
            this.node = node;
        }

        public Edge(int weight, int node, int parent) {
            this.weight = weight;
            this.node = node;
            this.parent = parent;
        }


        public String toString() {
            return String.format("[%d,%d]", node, weight);
        }
    }

    public int minimumSpanningTreeSumUsingPrim(int V, int[][] edges) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        createGraph(V, edges, graph);
        int[] visited = new int[V];
        Arrays.fill(visited, 0);
        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        q.offer(new Edge(0, 0));
        int sum = 0;
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            int currWt = curr.weight;
            int currNode = curr.node;
            if (visited[currNode] == 1) continue;
            visited[currNode] = 1;
            sum += currWt;
            for (Edge adj: graph.get(currNode)) {
                if (visited[adj.node] == 0)
                    q.offer(new Edge(adj.weight, adj.node));
            }
        }
        return sum;
    }

    public int[][] minimumSpanningTreeNodesUsingPrim(int V, int[][] edges) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        createGraph(V, edges, graph);
        int[] visited = new int[V];
        Arrays.fill(visited, 0);
        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        q.offer(new Edge(0, 0, -1));
        int sum = 0;
        ArrayList<int[]> mstNodes = new ArrayList<>();
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            int currWt = curr.weight;
            int currNode = curr.node;
            int currParent = curr.parent;
            if (currParent != -1 && visited[currNode] == 0) {
                mstNodes.add(new int[]{currParent, currNode});
            }
            if (visited[currNode] == 1) continue;
            visited[currNode] = 1;
            sum += currWt;
            for (Edge adj : graph.get(currNode)) {
                if (visited[adj.node] == 0) {
                    q.offer(new Edge(adj.weight, adj.node, currNode));
                }
            }
        }
        return mstNodes.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int V = 5;
        int[][] edges = {{0,1,2},{0,2,1},{1,2,1},{2,3,2},{2,4,2},{3,4,1}};
        MST_PrimsAlgorithm obj = new MST_PrimsAlgorithm();
        System.out.println(obj.minimumSpanningTreeSumUsingPrim(V, edges));
        System.out.println(Arrays.deepToString(obj.minimumSpanningTreeNodesUsingPrim(V, edges)));
    }

    private static void createGraph(int V, int[][] edges,  ArrayList<ArrayList<Edge>> graph) {
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());
        for (int[] edge: edges) {
            graph.get(edge[0]).add(new Edge(edge[2], edge[1]));
            graph.get(edge[1]).add(new Edge(edge[2], edge[0]));
        }
    }
}
