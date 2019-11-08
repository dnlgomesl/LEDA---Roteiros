package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (Util.verifca(leftIndex, rightIndex, array)){
			if (leftIndex < rightIndex){
				int mid = (rightIndex + leftIndex) / 2;
				sort(array, leftIndex, mid);
				sort(array, mid + 1, rightIndex);
				merge(array, leftIndex, rightIndex, mid);
			}
		}
	}

	private void merge(T[] array, int leftIndex, int rightIndex, int mid) {
		T[] copy = (T[]) new Comparable[array.length];
		System.arraycopy(array, 0, copy, 0, array.length);
		int i = leftIndex;
		int j = mid+1;
		for (int k = leftIndex; k <= rightIndex; k++){
			if (i > mid){
				array[k] = copy[j++];
			} else if (j > rightIndex){
				array[k] = copy[i++];
			}
			else if (copy[i].compareTo(copy[j]) < 0){
				array[k] = copy[i++];
			}else{
				array[k] = copy[j++];
			}
		}
	}
}
