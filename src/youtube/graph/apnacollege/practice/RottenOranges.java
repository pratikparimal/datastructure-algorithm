package youtube.graph.apnacollege.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href=https://www.youtube.com/watch?v=yf3oUhkvqA0>Rotten Oranges - Youtube></a>
 * <p>
 * Given an m x n grid, where each cell has the following values :
 * 2 - represents a rotten orange
 * 1 - represents a Fresh orange
 * 0 - represents an Empty Cell
 * Every minute, if a fresh orange is adjacent to a rotten orange in 4-direction ( upward, downwards, right, and left ) it becomes rotten.
 * Return the minimum number of minutes required such that none of the cells has a Fresh Orange.
 * If it's not possible, return -1.
 */
public class RottenOranges {

    static class Edge {
        int row, col, time;
        public Edge(int r, int c, int t) {
            this.row = r;
            this.col = c;
            this.time = t;
        }
    }

    public static int timeTakenToRotAllOranges(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        int countFreshOrange = 0;
        Queue<Edge> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new Edge(i, j, 0));
                    visited[i][j] = 2;
                }
                if (grid[i][j] == 1) countFreshOrange++;
            }
        }
        int[] r = {0, 0, -1, 1};
        int[] c = {-1, 1, 0, 0};
        int t = 0;
        int count = 0;
        while (!q.isEmpty()) {
            Edge curr = q.poll();
            int time = curr.time;
            t = Math.max(time, t);
            for (int i = 0; i < 4; i++) {
                int row = curr.row + r[i];
                int col = curr.col + c[i];
                if (row >= 0 && row < m && col >= 0 && col < n
                        && visited[row][col] != 2 && grid[row][col] == 1) {
                    q.add(new Edge(row, col, time + 1));
                    visited[row][col] = 2;
                    count++;
                }
            }
        }
        if (countFreshOrange != count) return -1;
        return t;
    }

    public static void main(String[] args) {
        int[][] grid = {{2,1,1}, {1,1,0}, {0,1,1}};
        int timeTaken = timeTakenToRotAllOranges(grid);
        System.out.println(timeTaken);
    }
}
