package youtube.graph.striver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DisjointSet {

    List<Integer> rank = new ArrayList<>();
    List<Integer> size = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public Integer findParent(int node) {
        if (parent.get(node) == node)
            return node;
        int ultimateParent = findParent(parent.get(node));
        parent.set(node, ultimateParent);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int ultimateParent_u = findParent(u);
        int ultimateParent_v = findParent(v);
        if (ultimateParent_u == ultimateParent_v)
            return;
        if (rank.get(ultimateParent_u) < rank.get(ultimateParent_v)) {
            parent.set(ultimateParent_u, ultimateParent_v);
        } else if (rank.get(ultimateParent_v) < rank.get(ultimateParent_u)) {
            parent.set(ultimateParent_v, ultimateParent_u);
        } else {
            parent.set(ultimateParent_v, ultimateParent_u);
            int rank_u = rank.get(ultimateParent_u);
            rank.set(ultimateParent_u, rank_u + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int ultimateParent_u = findParent(u);
        int ultimateParent_v = findParent(v);
        if (ultimateParent_u == ultimateParent_v)
            return;
        if (size.get(ultimateParent_u) < size.get(ultimateParent_v)) {
            parent.set(ultimateParent_u, ultimateParent_v);
            size.set(ultimateParent_v, size.get(ultimateParent_v) + size.get(ultimateParent_u));
        } else {
            parent.set(ultimateParent_v, ultimateParent_u);
            size.set(ultimateParent_u, size.get(ultimateParent_u) + size.get(ultimateParent_v));
        }
    }

    public static void main(String[] args) {
        testUnionByRank();
        testUnionBySize();
    }

    public static void testUnionByRank() {
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        //Q. Is 3 & 7 from the same component?
        if (Objects.equals(ds.findParent(3), ds.findParent(7)))
            System.out.println("Same Component");
        else System.out.println("Different Component");

        ds.unionByRank(3, 7);

        if (Objects.equals(ds.findParent(3), ds.findParent(7)))
            System.out.println("Same Component");
        else System.out.println("Different Component");
    }

    public static void testUnionBySize() {
        DisjointSet ds = new DisjointSet(7);
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        //Q. Is 3 & 7 from the same component?
        if (Objects.equals(ds.findParent(3), ds.findParent(7)))
            System.out.println("Same Component");
        else System.out.println("Different Component");

        ds.unionBySize(3, 7);

        if (Objects.equals(ds.findParent(3), ds.findParent(7)))
            System.out.println("Same Component");
        else System.out.println("Different Component");
    }
}
