package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		T menor = null;
		// Verificacoes
		if (array != null && array.length > 0 && k > 0 && k <= array.length) {
			// Menor comca como o primeiro
			menor = array[0];
			// index do maior comeca como o primeiro
			int indexMaior = 0;
			//For pra pegar o indice do maior e o menor
			for (int i = 1; i < array.length; i++) {
				if (array[i].compareTo(menor) <= 0) {
					menor = array[i];
				}
				if (array[i].compareTo(array[indexMaior]) > 0){
					indexMaior = i;
				}
			}
			// variavel a ser atualizada com o indice do menor
			int indexMenor;
			// for pra pegar a estatistica de ordem
			for (int i = 1; i <= k - 1; i++) {
				// atualiza o indice do menor
				indexMenor = this.selectionSort(array, menor, indexMaior);
				// atualiza o menor
				menor = array[indexMenor];
			}
		}
		return menor;
	}

	//pega o indice do menor
	public int selectionSort(T[] array, T menor, int index){
		int indexMenor = index;
		for (int i = 0; i < array.length; i++) {
			if ((array[indexMenor].compareTo(array[i]) > 0) && (array[i].compareTo(menor) > 0)){
				indexMenor = i;
			}
		}
		return indexMenor;
	}

}
