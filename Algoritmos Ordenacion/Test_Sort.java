/**
 * @author Resco
 */
public class Test_Sort {
    public static void main(String[] args) {
        int [] array = {4,3,1,9,3,2,10};
        
        System.out.println("El array desordenado es: ");
        for(int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i] + " ");
        }
        System.out.println();
        //Selection_Sort.Sort(array);
        Insertion_Sort.Sort(array);

        System.out.println("El array ordenado es: ");
        for(int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i] + " ");
        }
    }
}