package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

   @Override
   public void sort(Integer[] array, int leftIndex, int rightIndex) {
      if (array.length == 0) {
         return;
      }
      Integer[] arrayB = new Integer[array.length];
      Integer k = maior(array, leftIndex, rightIndex);
      Integer[] arrayC = new Integer[k + 1];
      for (int i = 0; i < arrayC.length; i++) {
         arrayC[i] = 0;
      }
      for (int j = leftIndex; j <= rightIndex; j++) {
         arrayC[array[j]]++;
      }
      for (int i = leftIndex + 1; i <= k; i++) {
         arrayC[i] = arrayC[i] + arrayC[i - 1];
      }
      for (int j = rightIndex; j >= leftIndex; j--) {
         arrayC[array[j]] = arrayC[array[j]] - 1;
         arrayB[arrayC[array[j]]] = array[j];
      }
      for (int i = 0; i < array.length; i++) {
         array[i] = arrayB[i];
      }
   }

   public static Integer maior(Integer[] array, int leftIndex, int rigthIndex) {
      int max = array[leftIndex];
      for (int i = leftIndex; i <= rigthIndex; i++) {
         if (array[i] > max) {
            max = array[i];
         }
      }
      return max;
   }

}
