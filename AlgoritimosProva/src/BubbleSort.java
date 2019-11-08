import java.util.Arrays;

public class BubbleSort {
    public static void sort(int[] array, int left, int right){
        boolean swaped = true;
        while (swaped){
            swaped = false;
            for (int i = left; i < right ; i++) {
                if(array[i] > array[i+1]){
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    swaped = true;
                }
            }
        }
    }

    public static void recursiveSort(int[] array, int left, int right, int cLeft){
        if(left > right){
            return;
        }
        if(left == right){
            recursiveSort(array, cLeft, right-1, cLeft);
        }
        else {
            if(array[left] > array[left+1]){
                int temp = array[left];
                array[left] = array[left+1];
                array[left+1] = temp;
            }
            recursiveSort(array, left+1, right, cLeft);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 34, 4, 2, 3, 0, 25};
        int[] arr2 = {1, 34, 4, 2, 3, 0, 25};
        int left = 2;
        int rigth = arr.length-3;
        sort(arr2, left, rigth);
        recursiveSort(arr, left, rigth, left);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }
}
