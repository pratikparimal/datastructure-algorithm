package youtube.graph.striver;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceInABinaryMaze {

    static class Node {
        int row, col;
        int dis;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
            this.dis = 0;
        }

        public Node(int row, int col, int dis) {
            this.row = row;
            this.col = col;
            this.dis = dis;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1,1,1,1},
                        {1,1,0,1},
                        {1,1,1,1},
                        {1,1,0,0},
                        {1,0,0,1}};
        int[] source = {0,1};
        int[] destination = {2,2};

        Node src = new Node(source[0], source[1]);
        Node dest = new Node(destination[0], destination[1]);
        int shortestDistance = shortestPath(grid, src, dest);
        System.out.println(shortestDistance);
    }

    private static int shortestPath(int[][] grid, Node src, Node dest) {
        int[] r = {0, 0, 1, -1};
        int[] c = {-1, 1, 0, 0};
        int m = grid.length;
        int n = grid[0].length;
        if (grid[dest.row][dest.col] == 0)
            return -1;
        int[][] distance = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = (int) 10e9;
            }
        }
        distance[src.row][src.col] = 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(src.row, src.col, 0));
        while (!q.isEmpty()) {
            Node curr = q.poll();
            int currRow = curr.row;
            int currCol = curr.col;
            int currDis = curr.dis;
            for (int i = 0; i < 4; i++) {
                int adjRow = currRow + r[i];
                int adjCol = currCol + c[i];
                int adjDistSum = currDis + 1;
                if (adjRow >= 0 && adjRow < m && adjCol >= 0 && adjCol < n
                        && grid[adjRow][adjCol] != 0 && adjDistSum < distance[adjRow][adjCol]) {
                    distance[adjRow][adjCol] = adjDistSum;
                    if (adjRow == dest.row && adjCol == dest.col)
                        return distance[adjRow][adjCol];
                    q.offer(new Node(adjRow, adjCol, adjDistSum));
                }
            }
        }
        return -1;
    }
}
