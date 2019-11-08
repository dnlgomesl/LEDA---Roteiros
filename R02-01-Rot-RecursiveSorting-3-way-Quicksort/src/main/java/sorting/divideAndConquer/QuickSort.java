package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

import static util.Util.swap;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a particion element and rearranges the elements of the interval in such a way
 * that all elements lesser than the particion go to the left part of the array and
 * all elements greater than the particion, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(Util.verifca(leftIndex, rightIndex, array)) {
			if (leftIndex < rightIndex) {
				int j = particion(array, leftIndex, rightIndex);
				sort(array, leftIndex, j);
				sort(array, j + 1, rightIndex);
			}
		}
	}

	private int particion(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex + 1;
		int j = rightIndex;
		T pivot = array[leftIndex];
		while (i <= j){
			if (array[i].compareTo(pivot) < 0){
				i++;
			} else if(array[j].compareTo(pivot) > 0){
				j--;
			} else {
				swap(array, i, j);
				i++;
				j--;
			}
		}
		swap(array, leftIndex, j);
		return j;
	}
}
