package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CityWithSmallestNumberOfNeighbourAtThresholdDistance {

    public static void main(String[] args) {
        int n = 4, m = 4;
        int[][] edges = {{0,1,3}, {1,2,1}, {2,3,1}, {3,1,4}};
        int distanceThreshold = 4;
        int city = findCityFloydWarshall(n, m, edges, distanceThreshold);
        System.out.println(city);
        int city1 = findCityUsingDijkastra(n, m, edges, distanceThreshold);
        System.out.println(city1);
    }

    private static int findCityFloydWarshall(int n, int m, int[][] edges, int distanceThreshold) {
        int[][] distance = new int[n][n];
        for (int[] ar: distance)
            Arrays.fill(ar, (int)1e9);
        for (int i = 0; i < m; i++) {
            int row = edges[i][0];
            int col = edges[i][1];
            int wt  = edges[i][2];
            distance[row][col] = wt;
            distance[col][row] = wt;
        }
        for (int i = 0; i < n; i++)
            distance[i][i] = 0;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] == 1e9 || distance[k][j] == 1e9)
                        continue;
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }

        int cntCity = n + 1;
        int city = -1;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (distance[i][j] <= distanceThreshold)
                    cnt++;
            }
            if (cnt <= cntCity) {
                cntCity = cnt;
                city = i;
            }
        }
        return city;
    }

    static class Edge{
        int node, wt;
        public Edge(int node, int wt) {
            this.node = node;
            this.wt = wt;
        }
    }

    private static int[] dijkastra(int n, ArrayList<ArrayList<Edge>> graph, int src) {
        int[] distance = new int[n];
        Arrays.fill(distance, (int)1e9);
        distance[src] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.wt));
        pq.offer(new Edge(src, 0));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int currNode = curr.node;
            int currWt = curr.wt;
            for (Edge adj: graph.get(currNode)) {
                int adjNode = adj.node;
                int adjWt = adj.wt;
                if (currWt + adjWt < distance[adjNode]) {
                    distance[adjNode] = currWt + adjWt;
                    pq.offer(new Edge(adjNode, currWt + adjWt));
                }
            }
        }
        return distance;
    }

    private static int findCityUsingDijkastra(int n, int m, int[][] edges, int distanceThreshold) {
        //create graph
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        //insert data into graph
        for (int[] edge: edges) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            graph.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }
        int[][] costMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            costMatrix[i] = dijkastra(n, graph, i);
        }
        int cntCity = n + 1;
        int city = -1;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (costMatrix[i][j] <= distanceThreshold)
                    cnt++;
            }
            if (cnt <= cntCity) {
                cntCity = cnt;
                city = i;
            }
        }
        return city;
    }
}
