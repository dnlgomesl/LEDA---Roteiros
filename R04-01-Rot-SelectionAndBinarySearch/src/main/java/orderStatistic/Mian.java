package orderStatistic;

import sun.awt.windows.WBufferStrategy;

public class Mian {
    public static void main(String[] args) {
        int[] array = {6, 8, 3, 3, 5, 9};
        System.out.println(count(array, 3, 0));
    }


    public static int count(int[] array, int elem, int cont){
        int a = buscaBin(array, elem, 0, array.length-1);
        if (a!= -1){
            cont ++;
            count(array, elem, cont);
        }
        return cont;
    }

    private static int buscaBin(int[] array, int elem, int start, int end) {
        if(elem > array[start]){
            return -1;
        }
        if (start == end){
            return -1;
        }
        int meio = (start + end) / 2;
        if(array[meio] == elem){
            return meio;
        }
        if(array[meio] > elem){
            return buscaBin(array, elem, start, meio-1);
        } else {
            return buscaBin(array, elem, meio+1, end);
        }
    }
}
