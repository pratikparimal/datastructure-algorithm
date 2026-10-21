package youtube.striver.recursion.subsequence;

import java.util.ArrayList;
import java.util.List;

public class SubsequenceOfAnArray_1 {

    public void subsequence(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        // base case
        if (index == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        //two cases here, take and not take the index into consideration
        //case 1: take
        current.add(arr[index]);
        subsequence(arr, index + 1, current, result);
        current.remove(current.getLast());
        // case 2: don't take (we have removed the index in the above line from the current list)
        subsequence(arr, index + 1, current, result);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};
        List<List<Integer>> result = new ArrayList<>();
        new SubsequenceOfAnArray_1().subsequence(arr, 0, new ArrayList<>(), result);
        System.out.println(result);
    }
}
