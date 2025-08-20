package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CheapestFlightWithKStops {

    static class Edge {
        int node, price;
        public Edge(int node, int price) {
            this.node = node;
            this.price = price;
        }
    }

    static class Path {
        int node, price, stopCount;
        public Path(int node, int price, int stopCount) {
            this.node = node;
            this.price = price;
            this.stopCount = stopCount;
        }
    }

    private int cheapestFlight(int n, int[][] flights, int src, int dest, int k) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        createGraph(flights, graph);
        int[] prices = new int[n];
        Arrays.fill(prices, (int)10e9);
        prices[src] = 0;
        Queue<Path> q = new LinkedList<>();
        q.offer(new Path(src, 0, 0));
        while (!q.isEmpty()) {
            Path currPath = q.poll();
            int currNode = currPath.node;
            int currPrice = currPath.price;
            int currStopCount = currPath.stopCount;
            if (currStopCount > k) continue;
            for (Edge adj: graph.get(currNode)) {
                int adjNode = adj.node;
                int adjPrice = adj.price;
                int adjStopCount = currStopCount + 1;
                if (adjPrice + currPrice < prices[adjNode] && currStopCount <= k) {
                    prices[adjNode] = adjPrice + currPrice;
                    q.offer(new Path(adjNode, adjPrice + currPrice, adjStopCount));
                }
            }
        }
        return (prices[dest] < (int)10e9) ? prices[dest] : -1 ;
    }

    private void createGraph(int[][] flights, ArrayList<ArrayList<Edge>> graph) {
        int n = flights.length;
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (int[] flight : flights)
            graph.get(flight[0]).add(new Edge(flight[1], flight[2]));
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 1;
        int src = 0;
        int dest = 3;
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        CheapestFlightWithKStops obj = new CheapestFlightWithKStops();
        int cost = obj.cheapestFlight(n, flights, src, dest, k);
        System.out.println(cost);
    }
}
