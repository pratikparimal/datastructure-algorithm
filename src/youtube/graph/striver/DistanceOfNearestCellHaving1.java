package youtube.graph.striver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCellHaving1 {

    static class Edge {
        int row, col, dist;
        public Edge(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    private static int[][] nearest(int[][] graph) {
        int[][] tempGraph = graph.clone();
        int m = tempGraph.length;
        int n = tempGraph[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] distance = new int[m][n];
        Queue<Edge> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    q.offer(new Edge(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            Edge e = q.poll();
            distance[e.row][e.col] = e.dist;
            for (int i = 0; i < 4; i++) {
                int row = e.row + r[i];
                int col = e.col + c[i];
                if (row >= 0 && row < m && col >= 0 && col < n
                    && !visited[row][col]) {
                    visited[row][col] = true;
                    q.offer(new Edge(row, col, e.dist + 1));
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        int[][] graph = {{1,0,1},{1,1,0},{1,0,0}};
        int[][] resultant = nearest(graph);
        System.out.println(Arrays.deepToString(resultant));
    }
}
