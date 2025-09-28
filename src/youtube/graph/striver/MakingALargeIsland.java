package youtube.graph.striver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MakingALargeIsland {

    static class DisjointSet {
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet(int n) {
            for (int i = 0; i < n; i++) {
                parent.add(i);
                size.add(1);
            }
        }

        public int findParent(int node) {
            int immParent = parent.get(node);
            if (immParent == node) return node;
            int ultParent = findParent(immParent);
            parent.set(node, ultParent);
            return parent.get(node);
        }

        public void unionBySize(int u, int v) {
            int parentU = findParent(u);
            int parentV = findParent(v);
            if (parentU == parentV) return;
            if (size.get(parentU) > size.get(parentV)) {
                parent.set(parentV, parentU);
                size.set(parentU, size.get(parentV) + size.get(parentU));
            } else {
                parent.set(parentU, parentV);
                size.set(parentV, size.get(parentV) + size.get(parentU));
            }
        }
    }

    public static int largestIsland(int n, int[][] island) {
        DisjointSet ds = new DisjointSet(n * n);
        int maxCount = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int[] r = {0, 0, 1, -1};
                int[] c = {-1, 1, 0, 0};
                if (island[row][col] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int tr = r[k] + row;
                    int tc = c[k] + col;
                    if (isValid(tr, tc, n) && island[tr][tc] == 1) {
                        int node = row * n + col;
                        int adjNode = tr * n + tc;
                        ds.unionBySize(node, adjNode);
                    }
                }
            }
        }
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (island[row][col] == 1) continue;
                HashSet<Integer> set = new HashSet<>();
                int[] r = {0, 0, 1, -1};
                int[] c = {-1, 1, 0, 0};
                for (int k = 0; k < 4; k++) {
                    int tr = r[k] + row;
                    int tc = c[k] + col;
                    if (isValid(tr, tc, n) && island[tr][tc] == 1) {
                        int adjNode = tr * n + tc;
                        int adjParent = ds.findParent(adjNode);
                        set.add(adjParent);
                    }
                }
                int count = 0;
                for (int parent: set) {
                    count += ds.size.get(parent);
                }
                maxCount = Math.max(maxCount, count + 1);
            }
        }
        for (int cell = 0; cell < n * n; cell++) {
            maxCount = Math.max(maxCount, ds.size.get(ds.findParent(cell)));
        }
        return maxCount;
    }

    private static boolean isValid(int tr, int tc, int n) {
        return tr >= 0 && tr < n && tc >= 0 && tc < n;
    }

    public static void main(String[] args) {
        int n = 2;
        int[][] island = {{1,0},{0,1}};
        System.out.println(largestIsland(n, island));
    }
}
