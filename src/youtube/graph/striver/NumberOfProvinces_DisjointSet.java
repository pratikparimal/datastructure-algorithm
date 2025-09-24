package youtube.graph.striver;

public class NumberOfProvinces_DisjointSet {

    public int numberOfProvinces(int[][] provinces, int V) {
        DisjointSet ds = new DisjointSet(V);
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (provinces[i][j] == 1) {
                    ds.unionBySize(i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (ds.parent.get(i) == i) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int [][] provinces = {
                {0,1,0,0,0,0,0},
                {1,0,1,0,0,0,0},
                {0,1,0,0,0,0,0},
                {0,0,0,0,1,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,0,0,1},
                {0,0,0,0,0,1,0}};
        int V = 7;
        System.out.println(new NumberOfProvinces_DisjointSet().numberOfProvinces(provinces, V));
    }
}
