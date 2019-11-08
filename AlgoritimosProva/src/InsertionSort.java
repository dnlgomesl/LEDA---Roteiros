public class InsertionSort {

     public static void sort(int[] array, int left, int right){
         for (int j = left+1; j <= right ; j++) {
             int key = array[j];
             int i = j-1;
             while (i >= left && array[i] > key){
                 array[i+1] = array[i];
                 i--;
             }
             array[i+1] = key;
         }

     }

    public static void recursiveSort(int[] array, int left, int right){
         if (left > right){
             return;
         }
         recursiveSort(array, left, right-1);
         int key = array[right];
         int i = right - 1;
         while (i >= left && array[i] > key){
             array[i+1] = array[i];
             i--;
         }
         array[i+1] = key;
    }

}
