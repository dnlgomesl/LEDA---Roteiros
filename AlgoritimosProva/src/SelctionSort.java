import java.util.Arrays;

public class SelctionSort {

    public static void sort(int[] array, int left, int right){
        for (int i = left; i <  right ; i++) {
            int menor = i;
            for (int j = left+1; j <= right ; j++) {
                if(array[j] < array[menor]){
                    menor = j;
                }
            }
            int temp = array[i];
            array[i] = array[menor];
            array[menor] = temp;
        }
    }

    public static void recursiveSort(int[] array, int left, int right){
        if(left > right){
            return;
        }
        int k = minIndex(array, left, right);
        if(k != left){
            int temp = array[left];
            array[left] = array[k];
            array[k] = temp;
        }
        recursiveSort(array, left+1, right);
    }

    private static int minIndex(int[] array, int left, int right) {
        if (left == right){
            return left;
        }
        int k =  minIndex(array, left+1, right);
        if(array[left] < array[k]){
            return left;
        }
        return k;
    }

    public static void main(String[] args) {
        int[] arr = {1, 34, 4, 2, 3, 0, 25};
        int[] arr2 = {1, 34, 4, 2, 3, 0, 25};
        int left = 2;
        int rigth = arr.length-3;
        sort(arr2, left, rigth);
        recursiveSort(arr, left, rigth);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }
}
