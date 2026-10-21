package youtube.striver.recursion.subsequence;

import java.util.ArrayList;
import java.util.List;

public class CountNoOfSubsequenceWithSumK {

    public int countNoOfSubsequenceWithSumK(int[] arr, int index, int currSum, int sumK, List<Integer> current) {
        if (index == arr.length) {
            return currSum == sumK ? 1 : 0;
        }
        current.add(arr[index]);
        currSum += arr[index];
        int l = countNoOfSubsequenceWithSumK(arr, index + 1, currSum, sumK, current);
        current.removeLast();
        currSum -= arr[index];
        int r = countNoOfSubsequenceWithSumK(arr, index + 1, currSum, sumK, current);
        return l + r;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 2};
        int sumK = 3;
        int count = new CountNoOfSubsequenceWithSumK().countNoOfSubsequenceWithSumK(arr, 0, 0, sumK, new ArrayList<>());
        System.out.println(count);
    }
}
