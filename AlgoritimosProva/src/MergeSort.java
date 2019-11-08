public class MergeSort {
    public static void sort(int[] array, int left, int right){
        int middle = (left + right) / 2;
        sort(array, left, middle);
        sort(array, middle+1, right);
        merge(array, left, right, middle);
    }

    public static void merge(int[] array, int left, int right, int middle) {
          int i = left;
          int j =  middle+1;
          int[] copy = new int[array.length];
          System.arraycopy(array, 0, copy, 0, array.length);
        for (int k = left; k <= right; k++) {
            if(i > middle){
                    array[k] = copy[j++];
            }
            if(j > right){
                array[k] = copy[i++];
            }
            else if(copy[i] < copy[j]){
                array[k] = copy[i++];
            }
            else {
                array[k] = copy[j++];
            }
        }
    }
}
