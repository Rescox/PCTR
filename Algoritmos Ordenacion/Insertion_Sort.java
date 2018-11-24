/**
 * @author Resco
 */
public class Insertion_Sort {
    
    public static void Sort(int[] array) {
        int iArrayLength = array.length;
        for(int i = 1; i < iArrayLength; i++) {
            int iNumeroActual = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > iNumeroActual) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = iNumeroActual;
        }
    }
}