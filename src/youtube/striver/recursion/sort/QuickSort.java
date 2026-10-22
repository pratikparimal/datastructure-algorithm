package youtube.striver.recursion.sort;

public class QuickSort {

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (i <= high - 1 && arr[i] <= pivot)
                i++;
            while (j >= low + 1 && arr[j] > pivot)
                j--;
            if (i < j)
                swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 4, 1, 9, 2, 7, 6, 3, 8, 5};
        QuickSort qs = new QuickSort();
        qs.quickSort(arr, 0, arr.length - 1);
        qs.print(arr);
    }

    private void print(int[] arr) {
        for (int i: arr) {
            System.out.print(i + " ");
        }
    }
}
