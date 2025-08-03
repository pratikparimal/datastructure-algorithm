package youtube.graph.striver;

import java.util.*;

/*
Given two distinct words startWord and targetWord,
and a list denoting wordList of unique words of equal lengths.
Find the length of the shortest transformation sequence from startWord to targetWord.
Keep the following conditions in mind:

A word can only consist of lowercase characters.
Only one letter can be changed in each transformation.
Each transformed word must exist in the wordList including the targetWord.
startWord may or may not be part of the wordList
The second part of this problem can be found here.

Note: If no possible way to transform sequence from startWord to targetWord return 0

Example 1:

Input:
wordList = {"des","der","dfr","dgt","dfs"}
startWord = "der", targetWord= "dfs",
Output:
3
Explanation:
The length of the smallest transformation
sequence from "der" to "dfs" is 3
i,e "der" -> "dfr" -> "dfs".

Example 2:

Input:
wordList = {"geek", "gefk"}
startWord = "gedk", targetWord= "geek",
Output:
2
Explanation:
gedk -> geek

Example 3:

Input:
wordList = {"poon", "plee", "same", "poie","plea","plie","poin"}
startWord = "toon", targetWord= "plea",
Output: 7
Explanation:
toon -> poon -> poin -> poie -> plie -> plee -> plea
 */
public class WordLadder1 {

    static class Edge {
        String word;
        int seqLen;
        public Edge(String word, int seqLen) {
            this.word = word;
            this.seqLen = seqLen;
        }
    }

    public int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList(wordList));
        ArrayList<Character> letters = new ArrayList<>();
        list.forEach(x -> {
            for (Character c: x.toCharArray())
                if (!letters.contains(c))
                    letters.add(c);
        });
        Queue<Edge> queue = new LinkedList<>();
        queue.offer(new Edge(startWord, 1));
        list.remove(startWord);
        while (!queue.isEmpty()) {
            Edge currEdge = queue.poll();
            String currWord = currEdge.word;
            int seqLen = currEdge.seqLen;
            if (currWord.equals(targetWord)) return seqLen;
            for (int i = 0; i < currWord.length(); i++) {
                for (int j = 0; j < letters.size(); j++) {
                    if (currWord.charAt(i) != letters.get(j)) {
                        StringBuilder sb = new StringBuilder(currWord);
                        sb.setCharAt(i, letters.get(j));
                        if (list.contains(sb.toString())){
                            queue.offer(new Edge(sb.toString(), seqLen + 1));
                            list.remove(sb.toString());
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        //String[] wordList = {"des", "der", "dfr", "dgt", "dfs"};
        //String startWord = "der", targetWord= "dfs";
        //String[] wordList = {"geek", "gefk"};
        //String startWord = "gedk", targetWord= "geek";
        String[] wordList = {"poon", "plee", "same", "poie","plea","plie","poin"};
        String startWord = "toon", targetWord= "plea";
        System.out.println(new WordLadder1().wordLadderLength(startWord, targetWord, wordList));
    }
}
