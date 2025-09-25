package youtube.graph.striver;

import java.util.ArrayList;
import java.util.List;

public class ConnectingTheGraph {

    static class DisjointSet {
        public List<Integer> parent = new ArrayList<>();
        public List<Integer> size = new ArrayList<>();
        public List<Integer> rank = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                parent.add(i);
                size.add(1);
                rank.add(0);
            }
        }

        public int findParent(int node) {
            int p = parent.get(node);
            if (p == node)
                return node;
            int iParent = findParent(p);
            parent.set(node, iParent);
            return parent.get(node);
        }

        public void unionByRank(int u, int v) {
            int parentU = findParent(u);
            int parentV = findParent(v);
            if (parentU == parentV) return;
            if (rank.get(parentU) > rank.get(parentV)) {
                parent.set(parentV, parentU);
            } else if (rank.get(parentV) > rank.get(parentU)) {
                parent.set(parentU, parentV);
            } else {
                parent.set(parentV, parentU);
                rank.set(parentU, rank.get(parentU) + 1);
            }
        }

        public void unionBySize(int u, int v) {
            int parentU = findParent(u);
            int parentV = findParent(v);
            if (parentU == parentV) return;
            if (size.get(parentU) > size.get(parentV)) {
                parent.set(parentV, parentU);
                size.set(parentU, size.get(parentU) + size.get(parentV));
            } else {
                parent.set(parentU, parentV);
                size.set(parentV, size.get(parentV) + size.get(parentU));
            }
        }
    }

    public static int connect(int E, int V, int[][] edges) {
        DisjointSet ds = new DisjointSet(V);
        //find extra edges
        int countExtra = 0;
        for (int i = 0; i < E; i++) {
            if (ds.findParent(edges[i][0]) != ds.findParent(edges[i][1])) {
                ds.unionBySize(edges[i][0], edges[i][1]);
            } else {
                countExtra++;
            }
        }
        //find component count
        int countParent = 0;
        for (int i = 0; i < V; i++) {
            if (ds.parent.get(i) == i)
                countParent++;
        }
        if (countExtra >= countParent - 1)
            return countParent - 1;
        else return -1;
    }

    public static void main(String[] args) {
        int E = 8;
        int V = 9;
        int[][] edges = {{0,1},{0,2},{0,3},{1,2},{2,3},{4,5},{5,6},{7,8}};
        System.out.println(connect(E, V, edges));
    }
}
