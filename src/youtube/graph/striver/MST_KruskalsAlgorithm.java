package youtube.graph.striver;

import java.util.*;

public class MST_KruskalsAlgorithm {

    static class Edge {
        int weight, src, dest;
        public Edge(int weight, int src, int dest) {
            this.weight = weight;
            this.src = src;
            this.dest = dest;
        }

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }

        public String toString() {
            return String.format("(%d,%d)", src, dest);
        }
    }

    private static int minimumSpanningTreeSumUsingKruskal(int V, int[][] edges) {
        ArrayList<ArrayList<ArrayList<Integer>>> adjacencyList = new ArrayList<>();
        createGraph(V, edges, adjacencyList);
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) { //1 based indexing
            for (ArrayList<Integer> list : adjacencyList.get(i)) {
                int u = i;
                int v = list.get(1);
                int wt = list.get(0);
                edgeList.add(new Edge(wt, u, v));
            }
        }
        edgeList.sort(Comparator.comparingInt(a -> a.weight));
        DisjointSet ds = new DisjointSet(edgeList.size());
        ArrayList<Edge> mstEdges = new ArrayList<>();
        int wtSum = 0;
        for (Edge e : edgeList) {
            if (!Objects.equals(ds.findParent(e.src), ds.findParent(e.dest))) {
                ds.unionBySize(e.src, e.dest);
                mstEdges.add(new Edge(e.src, e.dest));
                wtSum += e.weight;
            }
        }
        System.out.println("MST edges: " + mstEdges);
        return wtSum;
    }

    public static void main(String[] args) {
        int V = 6;
        int[][] edges = {{1,4,1},{1,2,2},{2,3,3},{2,4,3},{1,5,4},{3,4,5},{2,6,7},{3,6,8},{4,5,9}};
        System.out.println("MST weight sum: " + minimumSpanningTreeSumUsingKruskal(V, edges));
    }

    private static void createGraph(int V, int[][] edges,  ArrayList<ArrayList<ArrayList<Integer>>> adjacencyList) {
        for (int i = 0; i <= V; i++) //1 based indexing
            adjacencyList.add(new ArrayList<>());
        for (int[] edge: edges) {
            adjacencyList.get(edge[0]).add(new ArrayList<>(List.of(edge[2], edge[1])));
            adjacencyList.get(edge[1]).add(new ArrayList<>(List.of(edge[2], edge[0])));
        }
    }
}
