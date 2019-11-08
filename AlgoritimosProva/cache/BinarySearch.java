public class BinarySearch {

    public static void search(Integer[] array, int k){
        search(array, 0, array.length-1, k);
    }

    public static Integer search(Integer[] array, int left, int rigth, int k) {
        if (array[left] > k) {
            return null;
        }
        if(array[rigth] < k) {
            return null;
        }
        if(array[left] == k){
            return array[left];
        }
        // se tiver retornando indice pode nao ser uma boa ideia essa condicao, pois pode querer a primeira ocorrencia
        if(array[rigth] == k){
            return array[rigth];
        }
        int middle = (left + rigth) / 2;
        // se tiver retornando indice pode nao ser uma boa ideia essa condicao, pois pode querer a primeira ocorrencia
        if(array[middle] == k){
            return array[middle];
        }
        if(array[middle] > k){
            return search(array, left, middle-1, k);
        }
        return search(array, middle+1, rigth, k);
    }

    public static Integer searchFloor(Integer[] array, int left, int rigth, int k){
        if (array[left] > k) {
            return null;
        }
        if(array[rigth] <= k){
            return array[rigth];
        }
        if(array[left] ==  k){
            return array[left];
        }
        int middle = (left+rigth) / 2;
        if(middle == left){
            return array[middle];
        }
        if(array[middle] == k){
            return array[middle];
        }
        if(array[middle] < k){
            return searchFloor(array, middle, rigth, k);
        }
        return searchFloor(array, left, middle-1, k);
    }

    public static Integer searchCeil(Integer[] array, int left, int rigth, int k){
        if (array[rigth] < k) {
            return null;
        }
        if(array[left] >= k){
            return array[left];
        }
        int middle = (left+rigth) / 2;
        if(middle == left){
            return array[middle];
        }
        if(array[middle] == k){
            return array[middle];
        }
        if(array[middle] < k){
            return searchCeil(array, middle+1, rigth, k);
        }
        return searchCeil(array, left, middle, k);
    }
}
