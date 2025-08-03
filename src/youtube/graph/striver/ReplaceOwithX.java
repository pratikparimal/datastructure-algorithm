package youtube.graph.striver;

import java.util.Arrays;

public class ReplaceOwithX {

    private static char[][] replaceXwithO(int m, int n, char[][] graph) {
        boolean[][] visited = new boolean[m][n];

        //traverse 1st and last row
        //check for 'o' at boundary
        //if found, do dfs to traverse all the 'o' nodes that are connected to it in all 4 directions to mark it visited
        //once marked visited that means, these nodes do not qualify to be replaced
        for (int col = 0; col < n; col++) {
            int row = 0;
            for (int i = 0; i < 2; i++) {
                if (graph[row][col] == 'o' && !visited[row][col])
                    dfs(row, col, graph, visited);
                row = m - 1;
            }
        }
        //traverse 1st and last column
        for (int row = 1; row < m - 1; row++) {
            int col = 0;
            for (int i = 0; i < 2; i++) {
                if (graph[row][col] == 'o' && !visited[row][col])
                    dfs(row, col, graph, visited);
                col = n - 1;
            }
        }

        //now traverse through the graph to find the 'o' that are not visited and replace it
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 'o' && !visited[i][j])
                    graph[i][j] = 'x';
            }
        }
        return graph;
    }

    private static void dfs(int row, int col, char[][] graph, boolean[][] visited) {
        int m = graph.length;
        int n = graph[0].length;
        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, 1, -1};
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int tr = row + r[i];
            int tc = col + c[i];
            if(tr >= 0 && tr < m && tc >= 0 && tc < n && !visited[tr][tc] && graph[tr][tc] == 'o') {
                dfs(tr, tc, graph, visited);
            }
        }
    }


    public static void main(String[] args) {
        char[][] graph =   {{'x','x','x','o','o'},
                            {'x','o','x','x','x'},
                            {'x','o','o','o','x'},
                            {'x','o','o','x','x'},
                            {'x','x','x','x','o'}};
        int m = graph.length;
        int n = graph[0].length;
        char[][] cloned = graph.clone();
        char[][] replaced = replaceXwithO(m, n, cloned);
        System.out.println(Arrays.deepToString(replaced));
    }
}
