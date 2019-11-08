package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array.length != 0){
			return searchBinaryFloor(array, 0, array.length-1, x);
		}
		return null;
	}

	public static Integer searchBinaryFloor(Integer[] array, int left, int right, Integer x) {
		if (x < array[left]) {
			return null;
		}
		if (x >= array[right]) {
			return array[right];
		}
		int mid = (left + right) / 2;
		if (mid == left) {
			return array[left];
		}
		if (array[mid] == x) {
			return array[mid];
		}
		else if (array[mid] < x) {
			return searchBinaryFloor(array, mid, right, x);
		}
		else {
			return searchBinaryFloor(array, left, mid - 1, x);
		}
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		if(array.length != 0){
			return searchBinaryCeil(array, 0, array.length-1, x);
		}
		return null;
	}

	public static Integer searchBinaryCeil(Integer[] array, int left, int right, Integer x) {
		if (x <= array[left]) {
			return array[left];
		}
		if (x > array[right]) {
			return null;
		}
		int mid = (left + right) / 2;
		if (array[mid] == x) {
			return array[mid];
		}
		else if (array[mid] < x) {
			return searchBinaryCeil(array, mid + 1, right, x);
		} else {
			return searchBinaryCeil(array, left, mid, x);
		}
	}
}
