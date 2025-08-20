package youtube.graph.striver;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {

    static class Node {
        int row, col, diff;
        public Node(int row, int col, int diff) {
            this.row = row;
            this.col = col;
            this.diff = diff;
        }
    }

    public static void main(String[] args) {
        //int[][] heights = {{1,2,2}, {3,8,2}, {5,3,5}};
        int[][] heights = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};
        int[] start = {0,0};
        int[] end = {heights.length - 1, heights[0].length - 1};
        int minEffort = shortestPathWithMinimumEffort(heights, start, end);
        System.out.println(minEffort);
    }

    private static int shortestPathWithMinimumEffort(int[][] heights, int[] start, int[] end) {
        int[] r = {0, 0, 1, -1};
        int[] c = {-1, 1, 0, 0};
        int m = heights.length;
        int n = heights[0].length;
        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                distance[i][j] = (int) 1e9;
        distance[start[0]][start[1]] = 0;
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.diff));
        q.offer(new Node(start[0], start[1], 0));
        while (!q.isEmpty()) {
            Node currNode = q.poll();
            int currRow = currNode.row;
            int currCol = currNode.col;
            int currDiff = currNode.diff;
            if (currRow == end[0] && currCol == end[1])
                return currDiff;
            for (int i = 0; i < 4; i++) {
                int adjRow = currRow + r[i];
                int adjCol = currCol + c[i];
                if (adjRow >= 0 && adjRow < m && adjCol >= 0 && adjCol < n) {
                    int pathDiff = Math.abs(heights[currRow][currCol] - heights[adjRow][adjCol]);
                    int maxEffort = Math.max(currDiff, pathDiff);
                    if (maxEffort < distance[adjRow][adjCol]) {
                        distance[adjRow][adjCol] = maxEffort;
                        q.offer(new Node(adjRow, adjCol, maxEffort));
                    }
                }
            }
        }
        return -1;
    }
}
