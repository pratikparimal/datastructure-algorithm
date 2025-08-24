package youtube.graph.striver;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Bellman Ford:
 * Finds the shortest distance from a source to all the nodes
 * in a directed graph having -ve weight edges.

 * Intuition:
 * For n nodes, relax each node n-1 times
 * relaxation means,
 * if(dist[src] + wt < dist[dest])
 *      dist[dest] = dist[src] + wt
 * At end of loop, we will have dist[] with the shortest distance
 * to each node from src node

 * To find, if graph has a -ve cycle,
 * relax one more time and see if dist[src] + wt < dist[dest]
 * if yes, then it is a -ve weight cyclic graph
 */
public class BellmanFordAlgorithm {

    public static void main(String[] args) {
        int N = 6;
        int S = 0;
        int[][] edges = {{0,1,5},{1,2,-2},{1,5,-3},{5,3,1},{3,2,6},{2,4,3},{3,4,-2}};
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        createGraph(edges, graph);
        int[] distance = bellman_ford(N, graph, S);
        System.out.println(Arrays.toString(distance));
    }

    /*
    Representation:
    graph[index] -> ArrayList<Integer> temp
    temp[0] -> src
    temp[1] -> dest
    temp[2] -> weight
     */
    private static int[] bellman_ford(int N, ArrayList<ArrayList<Integer>> graph, int S) {
        int[] distance = new int[N];
        Arrays.fill(distance, (int)1e8);
        distance[S] = 0;
        for (int i = 0; i < N - 1; i++) { //relax each node n-1 time
            for (ArrayList<Integer> al: graph) {
                int src = al.get(0);
                int dest = al.get(1);
                int weight = al.get(2);
                if (distance[src] != 1e8 && distance[src] + weight < distance[dest]) {
                    distance[dest] = distance[src] + weight;
                }
            }
        }
        // relax all the node again for 1 time to check if negative cycle exist
        for (ArrayList<Integer> al: graph) {
            int src = al.get(0);
            int dest = al.get(1);
            int weight = al.get(2);
            if (distance[src] != 1e8 && distance[src] + weight < distance[dest]) { //if this condition is true, that means cycle exist
                return new int[]{-1};
            }
        }
        return distance;
    }

    private static void createGraph(int[][] edges, ArrayList<ArrayList<Integer>> graph) {
        for (int[] edge : edges) {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(edge[0]);
            al.add(edge[1]);
            al.add(edge[2]);
            graph.add(al);
        }
    }
}
