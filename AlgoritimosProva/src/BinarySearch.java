public class BinarySearch {

    public static Integer search(Integer[] array, int k){
        return search(array, 0, array.length-1, k);
    }

    public static Integer search(Integer[] array, int left, int rigth, int k) {
        if (array[left] > k){
            return null;
        }
        if(array[rigth] < k){
            return null;
        }
        if(array[left] == k){
            return array[left];
        }
        if(array[rigth] == k){
            return array[rigth];
        }
        int middle = (left + rigth) / 2;
        if(array[middle] == k){
            return array[middle];
        }
        if(array[middle] < k){
            return search(array, middle+1, rigth, k);
        }
        return search(array, left, middle-1, k);
    }

    public static Integer searchFloor(Integer[] array, int k){
        return searchFloor(array, 0, array.length-1, k);
    }

    public static Integer searchFloor(Integer[] array, int left, int rigth, int k){
        if (array[left] > k){
            return null;
        }
        if(array[left] == k){
            return array[left];
        }
        if(array[rigth] <= k){
            return array[rigth];
        }
        int middle = (left + rigth) / 2;
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

    public static Integer searchCeil(Integer[] array, int k){
        return searchCeil(array, 0, array.length-1, k);
    }

    public static Integer searchCeil(Integer[] array, int left, int rigth, int k){
        if (array[rigth] < k){
            return null;
        }
        if(array[left] >= k){
            return array[left];
        }
        int middle = (left + rigth) / 2;
        if(middle == left){
            return array[middle];
        }
        if(array[middle] == k){
            return array[middle];
        }
        else if(array[middle] < k){
            return searchCeil(array, middle+1, rigth, k);
        } else {
            return searchCeil(array, left, middle, k);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 4, 5, 7, 9, 20};
        System.out.println(search(arr, 4));
        System.out.println(searchFloor(arr, 3));
        System.out.println(searchCeil(arr, 3));
    }


}
