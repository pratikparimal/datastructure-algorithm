package youtube.striver.recursion.sort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public void mergeSort(int[] arr, int low, int high) {
        if (low == high)
            return;
        int mid = (low + high) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    //merge two sorted sub array
    //achieve this using two pointers - left and right pointer
    public void merge(int[] arr, int low, int mid, int high) {
        List<Integer> temp = new ArrayList<>();
        int leftPointer = low;
        int rightPointer = mid + 1;
        //compare left and right pointer until one of them reach its last element
        //add the smaller value to the temp array
        while(leftPointer <= mid && rightPointer <= high) {
            if (arr[leftPointer] <= arr[rightPointer]) {
                temp.add(arr[leftPointer]);
                leftPointer++;
            } else {
                temp.add(arr[rightPointer]);
                rightPointer++;
            }
        }
        //reiterate on left-over array and add all the remaining element from the arr to temp
        while(leftPointer <= mid) {
            temp.add(arr[leftPointer]);
            leftPointer++;
        }
        while (rightPointer <= high) {
            temp.add(arr[rightPointer]);
            rightPointer++;
        }
        //copy back the sorted data from temp to arr
        //so that we keep arr updated with sorted data at every recursion
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 1, 0, 5, 8, 2, 4, 3, 7, 6};
        new MergeSort().mergeSort(arr, 0, arr.length - 1);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
