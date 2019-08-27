package algorithms.sorting;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
        int unsorted_array[] = {3,9,6,1,0,8,2,7,6,6};
        System.out.print("UnSorted Array : ");
        sort.printArray(unsorted_array);
        int sorted_array[] = sort.bubbleSort(unsorted_array);
        System.out.print("Bubble Sorted : ");
        sort.printArray(sorted_array);
        sorted_array = sort.optimizedBubbleSort(unsorted_array);
        System.out.print("Optimized Bubble Sorted : ");
        sort.printArray(sorted_array);
        sorted_array = sort.recursiveBubbleSort(unsorted_array, unsorted_array.length);
        System.out.print("Recursive Bubble Sorted : ");
        sort.printArray(sorted_array);
    }

    private int[] bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }

    private int[] optimizedBubbleSort(int[] array) {
        int n = array.length;
        int changed = 0;
        for (int i = 0; i < n-1; i++){
            changed = 0;
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    changed = 1;
                }
            }
            if (changed == 0)
                break;
        }
        return array;
    }

    private int[] recursiveBubbleSort(int[] array, int n) {
        if (n == 1)
            return array;
        for (int i = 0; i < n-1; i++) {
            if (array[i] > array[i+1]) {
                int temp = array[i];
                array[i] = array[i+1];
                array[i+1] = temp;
            }
        }
        return recursiveBubbleSort(array, n-1);
    }

    private void printArray(int[] array) {
        Arrays.stream(array).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }
}
