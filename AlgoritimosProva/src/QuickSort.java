public class QuickSort {

    public static void sort(int[] array, int left, int right){
        int j = particion(array, left, right);
        sort(array, left, j);
        sort(array, j+1, right);
    }

    private static int particion(int[] array, int left, int right) {
        int i = left+1;
        int j = right;
        int pivot = array[left];
        while(i <= j){
            if(array[i] < pivot){
                i++;
            }
            else if (array[j] > pivot){
                j--;
            }
            else {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        int temp = array[j];
        array[j] = array[left];
        array[left] = temp;
        return j;
    }
}
