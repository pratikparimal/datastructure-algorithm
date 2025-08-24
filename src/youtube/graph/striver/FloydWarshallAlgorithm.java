package youtube.graph.striver;

import java.util.Arrays;

public class FloydWarshallAlgorithm {

    public static void main(String[] args) {
        int[][] matrix = {{0,1,43},{1,0,6},{-1,-1,0}};
        shortestPath(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    private static void shortestPath(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1)
                    matrix[i][j] = (int)1e9;
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Math.min
                            (matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1e9)
                    matrix[i][j] = -1;
            }
        }
    }
}
