package sorting;

public class Verificador {
    public static boolean verifca(int left, int rigth, Object[] array){
        if (left > rigth){
            return false;
        }
        if (array.length == 0){
            return false;
        }
        if (left < 0){
            return false;
        }
        if (rigth > array.length -1){
            return false;
        }
        return true;
    }
}
