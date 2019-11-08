package sorting.simpleSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex){
            int meio = (rightIndex + leftIndex) / 2;
            sort(array, leftIndex, meio);
            sort(array, meio + 1, rightIndex);
            merge(array, leftIndex, rightIndex, meio);
        }
    }

    private void merge(T[] array, int leftIndex, int rightIndex, int meio) {
        T[] copy = (T[]) new Object[array.length];
        for (int l = 0; l < array.length; l++){
            copy[l] = array[l];
        }
        int j = leftIndex;
        int k = meio+1;
        for (int i = leftIndex; i <= rightIndex; i++){
                if (j > meio){
                    array[i] = copy[k++];
                } else if (k > rightIndex){
                    array[i] = copy[j++];
                }
                else if (copy[j].compareTo(copy[i]) < 0){
                array[i] = copy[j++];
                }else{
                    array[i] = copy[k++];
                }
            }
    }
}

