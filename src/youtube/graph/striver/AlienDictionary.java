package youtube.graph.striver;

import java.util.*;

public class AlienDictionary {

    private static ArrayList<Character> toposort(int K, ArrayList<ArrayList<Integer>> graph) {
        ArrayList<Character> sorted = new ArrayList<>();
        //count indegree
        int[] indegree = new int[K];
        for (int i = 0; i < K; i++) {
            for (Integer j: graph.get(i)) {
                indegree[j]++;
            }
        }
        //insert node in q with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }
        //remove the edge between nodes to sort
        while(!q.isEmpty()) {
            int curr = q.poll();
            sorted.add((char)(curr + 'a'));
            for (Integer adj: graph.get(curr)) {
                indegree[adj]--;
                if (indegree[adj] == 0)
                    q.offer(adj);
            }
        }
        return sorted;
    }

    private static void createGraph(int N, int K, String[] words, ArrayList<ArrayList<Integer>> graph) {
        for (int i = 0; i < K; i++) {
            graph.add(new ArrayList<>());
        }
        //traverse through words to compare two string side by side to find order of words
        //ex. baa & abcd, abcd & abca .. so on
        for (int i = 0; i < N - 1; i++) {
            String a = words[i];
            String b = words[i + 1];
            int len = Math.min(a.length(), b.length());
            //compare a and b to find first character that is different
            for (int j = 0; j < len; j++) {
                char c = a.charAt(j);
                char d = b.charAt(j);
                if (c != d) {
                    graph.get(c - 'a').add(d - 'a');
                    break;
                }
            }
        }
    }

    private static String alienDictionary(int N, int K, String[] words) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        createGraph(N, K, words, graph);
        ArrayList<Character> dictionary = toposort(K, graph);
        StringBuilder result = new StringBuilder();
        for (char c: dictionary)
            result.append(c);
        return result.toString();
    }

    public static void main(String[] args) {
        int N = 5; // given - no. of words
        int K = 4; // given - no. of character in dictionary
        String[] words = {"baa", "abcd", "abca", "cab", "cad"}; // given - words array
        System.out.println(alienDictionary(N, K, words));
    }
}
