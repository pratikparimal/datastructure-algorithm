package algorithms.searching;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int array[] = {3,9,6,1,0,8,2,7,6};
        int n = array.length;
        Arrays.sort(array);
        boolean search = binarySearch(array, 0, n-1, 6);
        System.out.println("Element present ? : " + search);
    }

    private static boolean binarySearch(int[] array, int left, int right, int key) {
        if (right >= left) {
            int mid = left + (right - left)/2;
            if (array[mid] == key)
                return true;
            if (array[mid] > key)
                return binarySearch(array, left, mid-1, key);
            return binarySearch(array, mid+1, right, key);
        }
        return false;
    }
}
