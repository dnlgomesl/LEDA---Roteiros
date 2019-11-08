package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) {
			return;
		}
		Integer max = array[leftIndex];
		Integer min = array[leftIndex];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > max ) {
				max = array[i];
			}
			if (array[i] < min) {
				min = array[i];
			}
		}
		Integer[] arrayC = new Integer[(max - min) + 1];
		for(int i = 0; i < arrayC.length; i++) {
			arrayC[i] = 0;
		}
		for (int j = leftIndex; j <= rightIndex; j++) {
			arrayC[array[j] - min]++;
		}
		for (int i = leftIndex + 1; i <= arrayC.length - 1; i++) {
			arrayC[i] = arrayC[i] + arrayC[i - 1];
		}
		Integer[] arrayB = new Integer[rightIndex - leftIndex + 1];
		for (int j = rightIndex; j >= leftIndex; j--) {
			arrayC[array[j] - min] = arrayC[array[j] - min] - 1;
			arrayB[arrayC[array[j] - min]] = array[j];
		}
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = arrayB[i - leftIndex];
		}
	}
}

