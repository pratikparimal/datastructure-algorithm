package youtube.graph.apnacollege;

import java.util.ArrayList;

public class AllPathsFromSrcToTarget  {

    static class Edge {
        int src, dest;
        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    //Here we will keep track of path using String path
    //In basic dfs logic, whenever we visit any neighbour, we immediately make visited[i] to true
    //But here, in this case, as we have to keep on finding all the paths from src,
    //we will visit the neighbour, we will make it to true, and after coming back,
    // basically during backtracking of stack, we will make it to false again.
    //O(V^V) -> Exponential
    private static void modified_dfs(ArrayList<Edge>[] graph, int curr, boolean[] visited, String path, int target) {
        if (curr == target) {
            System.out.println(path);
            return;
        }
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (visited[e.dest] == false) {
                visited[curr] = true;
                modified_dfs(graph, e.dest, visited, path + e.dest, target);
                visited[curr] = false;
            }

        }
    }

    private static void createGraph(ArrayList<Edge>[] graph) {
        /*
            1 -- 3
          /      | \
        0        |   5 -- 6
          \      | /
            2 -- 4
         */
        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));
        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));
        graph[6].add(new Edge(6, 5));
    }

    public static void main(String[] args) {
        int v = 7;
        ArrayList<Edge>[] graph = new ArrayList[v];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        createGraph(graph);
        boolean[] visited = new boolean[v];
        String path = "0";
        modified_dfs(graph, 0, visited, path, 5);
    }
}
