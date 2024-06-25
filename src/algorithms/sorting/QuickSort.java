package algorithms.sorting;

public class QuickSort {

    public static void swap(int[] arr, int low, int high) {
        int temp = arr[low];
        arr[low] = arr[high];
        arr[high] = temp;
    }

    public static int partition(int[] arr, int low, int high) {
        int i = low - 1;
        int pivot = arr[high];
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static int[] quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {6, 1, 8, 2, 0, 9, 5};
        int[] sorted = quickSort(arr, 0, arr.length - 1);
        for (int i : sorted)
            System.out.print(i + " ");
    }
}
