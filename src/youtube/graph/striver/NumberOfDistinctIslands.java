package youtube.graph.striver;

import java.util.*;

public class NumberOfDistinctIslands {

    public class Edge {
        int row, col;
        public Edge(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            return this.row == e.row && this.col == e.col;
        }
        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }

    private ArrayList<Edge> bfs(int startRow, int startCol, int[][] graph, boolean[][] visited) {
        int m = graph.length;
        int n = graph[0].length;
        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, 1, -1};
        ArrayList<Edge> al = new ArrayList<>();
        Queue<Edge> q = new LinkedList<>();
        q.offer(new Edge(startRow, startCol));
        visited[startRow][startCol] = true;
        al.add(new Edge(0, 0));
        while (!q.isEmpty()) {
            Edge e = q.poll();
            int row = e.row;
            int col = e.col;
            for (int i = 0; i < 4; i++) {
                int tr = row + r[i];
                int tc = col + c[i];
                if(tr >= 0 && tr < m && tc >= 0 && tc < n && !visited[tr][tc] && graph[tr][tc] == 1) {
                    q.offer(new Edge(tr, tc));
                    visited[tr][tc] = true;
                    al.add(new Edge(tr - startRow, tc - startCol));
                }
            }
        }
        return al;
    }

    public int numberOfDistinctIslands(int[][] graph) {
        int m = graph.length;
        int n = graph[0].length;
        boolean[][] visited = new boolean[m][n];
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    ArrayList<Edge> al = bfs(i, j, graph, visited);
                    set.add(al.toString());
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[][] graph = {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
        NumberOfDistinctIslands n = new NumberOfDistinctIslands();
        System.out.println(n.numberOfDistinctIslands(graph));
    }
}
