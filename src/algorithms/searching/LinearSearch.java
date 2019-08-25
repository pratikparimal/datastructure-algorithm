package algorithms.searching;

public class LinearSearch {
    public static void main(String[] args) {
        int array[] = {3,9,6,1,0,8,2,7,6,6};
        boolean search = linearSearch(array, 8);
        System.out.println("Element present ? : " + search);
    }

    private static boolean linearSearch(int[] array, int key) {
        boolean search = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                search = true;
                break;
            }
        }
        return search;
    }
}
