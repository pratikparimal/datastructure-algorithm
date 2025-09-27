package youtube.graph.striver;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIsland2 {

    static class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i <= n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node) {
            int iParent = parent.get(node);
            if (iParent == node) return node;
            int tParent = findParent(iParent);
            parent.set(node, tParent);
            return parent.get(node);
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
                size.set(parentV, size.get(parentU) + size.get(parentV));
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int m = 5;
        int[][] island = {{0,0},{0,0},{1,1},{1,0},{0,1},{0,3},{1,3},{0,4},{3,2},{2,2},{1,2},{0,2}};
        ArrayList<Integer> islands = numberOfIsland(n, m, island);
        System.out.println(islands);
    }

    private static boolean isValid(int n, int m, int tr, int tc) {
        return tr >= 0 && tr < n && tc >= 0 && tc < m;
    }

    private static ArrayList<Integer> numberOfIsland(int n, int m, int[][] island) {
        DisjointSet ds = new DisjointSet(n * m);
        int count = 0;
        ArrayList<Integer> result = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < island.length; i++) {
            int row = island[i][0];
            int col = island[i][1];
            if (visited[row][col]) {
                result.add(count);
                continue;
            }
            visited[row][col] = true;
            count++;
            int[] r = {0, 0, 1, -1};
            int[] c = {-1, 1, 0, 0};
            for (int j = 0; j < 4; j++) {
                int adjR = r[j] + row;
                int adjC = c[j] + col;
                if (isValid(n, m, adjR, adjC) && visited[adjR][adjC]) {
                    int node = row * m + col;
                    int adjNode = adjR * m + adjC;
                    if (ds.findParent(node) != ds.findParent(adjNode)) {
                        count--;
                        ds.unionBySize(node, adjNode);
                    }
                }
            }
            result.add(count);
        }
        return result;
    }
}
