package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

import static util.Util.swap;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como particion,
	 * particionamos o array colocando os menores a esquerda do particion
	 * e os maiores a direita do particion, e depois aplicamos a mesma estrategia
	 * recursivamente na particao a esquerda do particion e na particao a direita do particion.
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o particion e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o particion
	 *   * arr[i+1..j-1] contem elementos iguais ao particion.
	 *   * arr[j..r] contem elementos maiores do que o particion.
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o particion. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (Util.verifca(leftIndex, rightIndex, array)){
			if (leftIndex < rightIndex){
				int j = leftIndex;
				int k = rightIndex;
				int i = leftIndex+1;
				T pivot = array[leftIndex];
				while (i <= k) {
					if (array[i].compareTo(pivot) < 0){
						swap(array, j++, i++);
					}
					else if (array[i].compareTo(pivot) > 0){
						swap(array, i, k--);
					}
					else {
						i++;
					}
				}
				sort(array, leftIndex, j - 1);
				sort(array, k + 1, rightIndex);
			}
		}
	}
}
