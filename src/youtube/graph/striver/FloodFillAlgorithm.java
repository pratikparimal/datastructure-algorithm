package youtube.graph.striver;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFillAlgorithm {

    static class Edge {
        int row, col;
        public Edge(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    public static void main(String[] args) {
        int [][] inputImage = {{1,1,1},{1,1,0},{1,0,1}};
        int[][] image = inputImage.clone();
        int sr = 1;
        int sc = 1;
        int newColor = 2;
        int m = image.length;
        int n = image[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] result = bfs(image, visited, sr, sc, newColor);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        visited = new boolean[m][n];
        int[][] result2 = dfs(image, visited, sr, sc, newColor);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result2[i][j]);
            }
            System.out.println();
        }

    }

    private static int[][] bfs(int[][] image, boolean[][] visited, int sr, int sc, int newColor) {
        int[] r = {0, 0, -1, 1};
        int[] c = {-1, 1, 0, 0};
        int m = image.length;
        int n = image[0].length;
        int oldColor = image[sr][sc];
        Queue<Edge> q = new LinkedList<>();
        q.offer(new Edge(sr, sc));
        visited[sr][sc] = true;
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            image[curr.row][curr.col] = newColor;
            for (int i = 0; i < 4; i++) {
                int row = curr.row + r[i];
                int col = curr.col + c[i];
                if (row >= 0 && row < m && col >= 0 && col < n &&
                    !visited[row][col] && image[row][col] == oldColor) {
                    q.offer(new Edge(row, col));
                    visited[row][col] = true;
                }
            }
        }
        return image;
    }

    private static int[][] dfs(int[][] image, boolean[][] visited, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        int[] r = {0, 0, -1, 1};
        int[] c = {-1, 1, 0, 0};
        int m = image.length;
        int n = image[0].length;
        image[sr][sc] = newColor;
        visited[sr][sc] = true;
        for (int i = 0; i < 4; i++) {
            int row = sr + r[i];
            int col = sc + c[i];
            if (row >= 0 && row < m && col >= 0 && col < n &&
                    !visited[row][col] && image[row][col] == oldColor) {
                dfs(image, visited, row, col, newColor);
            }
        }
        return image;
    }
}
