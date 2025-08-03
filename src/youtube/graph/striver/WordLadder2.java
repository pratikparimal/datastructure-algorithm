package youtube.graph.striver;

import java.util.*;

public class WordLadder2 {

    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordList) {
        ArrayList<String> wList = new ArrayList<>(Arrays.asList(wordList));
        ArrayList<ArrayList<String>> resultant = new ArrayList<>();
        Queue<ArrayList<String>> q = new LinkedList<>();
        q.offer(new ArrayList<>(List.of(startWord)));
        while (!q.isEmpty()) {
            ArrayList<String> currList = q.poll();
            System.out.println(currList);
            ArrayList<String> tempList = new ArrayList<>();
            String currWord = currList.getLast();
            if (currWord.equals(targetWord)) {
                resultant.add(currList);
                //continue;
            }
            for (int j = 0; j < currWord.length(); j++) {
                for (int k = 'a'; k <= 'z'; k++) {
                    if (currWord.charAt(j) != k) {
                        StringBuilder sb = new StringBuilder(currWord);
                        sb.setCharAt(j, (char) k);
                        if (wList.contains(sb.toString())) {
                            ArrayList<String> al = new ArrayList<>(currList);
                            al.add(sb.toString());
                            q.offer(al);
                            tempList.add(sb.toString());
                        }
                    }
                }
            }
            wList.removeAll(tempList);
        }
        return resultant;
    }

    public static void main(String[] args) {
        String[] wordList = {"des", "der", "dfr", "dgt", "dfs"};
        String startWord = "der", targetWord= "dfs";
        //String[] wordList = {"geek", "gefk"};
        //String startWord = "gedk", targetWord= "geek";
        //String[] wordList = {"poon", "plee", "same", "poie","plea","plie","poin"};
        //String startWord = "toon", targetWord= "plea";
        System.out.println(new WordLadder2().findSequences(startWord, targetWord, wordList));
    }
}
