/**
 * @author Resco
 */
public class Selection_Sort {
    
    public static void Sort(int array[]) {
        int iArraySize = array.length;
        for(int i = 0; i < iArraySize -1; i++) {
            int iMinimum = i;
            for(int j = i + 1; j < iArraySize; j++) { 
                if(array[j] < array[iMinimum])
                    iMinimum = j;
            }
            int aux = array[iMinimum];
            array[iMinimum] = array[i];
            array[i] = aux;
        }
    }
}