package youtube.graph.striver;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves {

    static class Edge {
        int row, col;
        public Edge(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static int noOfEnclavesBFS(int[][] graph) {
        int m = graph.length;
        int n = graph[0].length;
        boolean[][] visited = new boolean[m][n];

        //traverse through boundary of a matrix to find all the neighbour
        //row traversal for 0 and m-1
        for (int col = 0; col < n; col++) {
            int row = 0;
            for (int i = 0; i < 2; i++) {
                if (graph[row][col] == 1 && !visited[row][col])
                    bfs(row, col, graph, visited);
                row = m - 1;
            }
        }
        //column traversal for (1,0), (2,0)... (m-1,0) & (1,n-1), (2,n-1)... (m-1,n-1)
        for (int row = 1; row < m - 1; row++) {
            int col = 0;
            for (int i = 0; i < 2; i++) {
                if (graph[row][col] == 1 && !visited[row][col])
                    bfs(row, col, graph, visited);
                col = n - 1;
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1 && !visited[i][j])
                    count++;
            }
        }
        return count;
    }

    private static void bfs(int startRow, int startCol, int[][] graph, boolean[][] visited) {
        int m = graph.length;
        int n = graph[0].length;
        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, 1, -1};
        Queue<Edge> q = new LinkedList<>();
        q.offer(new Edge(startRow, startCol));
        visited[startRow][startCol] = true;
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
                }
            }
        }
    }

    private static int noOfEnclavesDFS(int[][] graph) {
        int m = graph.length;
        int n = graph[0].length;
        boolean[][] visited = new boolean[m][n];
        //traverse through boundary of a matrix to find all the neighbour
        //row traversal for 0 and m-1
        for (int col = 0; col < n; col++) {
            int row = 0;
            for (int i = 0; i < 2; i++) {
                if (graph[row][col] == 1 && !visited[row][col])
                    dfs(row, col, graph, visited);
                row = m - 1;
            }
        }
        //column traversal for (1,0), (2,0)... (m-1,0) & (1,n-1), (2,n-1)... (m-1,n-1)
        for (int row = 1; row < m - 1; row++) {
            int col = 0;
            for (int i = 0; i < 2; i++) {
                if (graph[row][col] == 1 && !visited[row][col])
                    dfs(row, col, graph, visited);
                col = n - 1;
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1 && !visited[i][j])
                    count++;
            }
        }
        return count;
    }

    private static void dfs(int row, int col, int[][] graph, boolean[][] visited) {
        int m = graph.length;
        int n = graph[0].length;
        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, 1, -1};
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int tr = row + r[i];
            int tc = col + c[i];
            if(tr >= 0 && tr < m && tc >= 0 && tc < n && !visited[tr][tc] && graph[tr][tc] == 1) {
                dfs(tr, tc, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{0,0,1,1},{0,1,0,0},{0,1,1,0},{0,0,0,1},{0,1,1,0}};
        int noOfEnclaves = noOfEnclavesDFS(graph);
        System.out.println(noOfEnclaves);
        noOfEnclaves = noOfEnclavesBFS(graph);
        System.out.println(noOfEnclaves);
    }
}
