package algorithms.sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        int unsorted_array[] = {3,9,1,0,8,2,7,6};
        int sorted_array[] = sort.selectionSort(unsorted_array);
        sort.printArray(sorted_array);
    }

    private void printArray(int[] array) {
        Arrays.stream(array).forEach(System.out::print);
    }

    private int[] selectionSort(int[] array) {
        int n = array.length;
        System.out.println(n);
        for (int i=0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (array[j] < array[min_idx])
                    min_idx = j;
            }
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
