package youtube.striver.recursion.subsequence;

import java.util.ArrayList;
import java.util.List;

public class AnyOneSubsequenceWithSumK_3 {

    public boolean subsequenceWithSumK(int[] arr, int index, int currSum, int sumK, List<Integer> current, List<List<Integer>> result) {
        //base case
        if (index == arr.length) {
            if (currSum == sumK) {
                result.add(new ArrayList<>(current));
                return true;
            }
            return false;
        }
        current.add(arr[index]);
        currSum += arr[index];
        if (subsequenceWithSumK(arr, index + 1, currSum, sumK, current, result))
            return true;
        current.removeLast();
        currSum -= arr[index];
        if (subsequenceWithSumK(arr, index + 1, currSum, sumK, current, result))
            return true;
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 2};
        int sumK = 3;
        List<List<Integer>> result = new ArrayList<>();
        new AnyOneSubsequenceWithSumK_3().subsequenceWithSumK(arr, 0, 0, sumK, new ArrayList<>(), result);
        System.out.println(result);
    }
}
