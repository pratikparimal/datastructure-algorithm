package youtube.graph.apnacollege.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an m x n 2D binary grid which represents a map of '1's (land) and '0's
 * (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 */
public class NumberOfIslands {

    static class Edge {
        int row, col;
        public Edge(int r, int c) {
            this.row = r;
            this.col = c;
        }
    }

    private static void bfs(int row, int col, int[][] visited, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] delRow = {0, 0, -1, 1};
        int[] delCol = {-1, 1, 0, 0};

        Queue<Edge> q = new LinkedList<>();
        q.offer(new Edge(row, col));
        visited[row][col] = 1;
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int r = curr.row + delRow[i];
                int c = curr.col + delCol[i];
                if (r >= 0 && r < m && c >= 0 && c < n
                        && visited[r][c] == 0 && grid[r][c] == 1) {
                    q.offer(new Edge(r, c));
                    visited[r][c] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[][] grid = {{1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0},
                        {0, 0, 1, 0, 0},
                        {0, 0, 0, 1, 1}};

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0 && grid[i][j] == 1) {
                    count++;
                    bfs(i, j, visited, grid);
                }
            }
        }
        System.out.println(count);
    }
}
