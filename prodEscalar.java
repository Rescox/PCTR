import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class prodEscalar implements Callable {
    public static float[] fVector1 = new float[10000];
    public static float[] fVector2 = new float[10000];
    public int iPosicion;
    public Float call() {
        Float fResultadoParcial = (float)0.0;
        for(int i = 2500 * iPosicion; i < 2500 * (iPosicion + 1) ; i++) {
            
            fResultadoParcial += fVector1[i] * fVector2[i];
        }
        System.out.println(fResultadoParcial);
        return fResultadoParcial;
    }
    
    public prodEscalar(int iTam) {
        iPosicion = iTam;
    }

    public static void main(String[] args) {
        Random r = new Random();
        for(int i = 0; i < 10000; i++) {
            fVector1[i] = 2;
        }

        for(int j = 0; j < 10000; j++) {
            fVector2[j] = 2;
        }

        ExecutorService exe = Executors.newFixedThreadPool(4);
        
        ArrayList<Future<Float>> list = new ArrayList<Future<Float>>();

        Callable<Float> callable1 = new prodEscalar(0);
        Callable<Float> callable2 = new prodEscalar(1);
        Callable<Float> callable3 = new prodEscalar(2);
        Callable<Float> callable4 = new prodEscalar(3);

       
        Future<Float> future1 = exe.submit(callable1);
        Future<Float> future2 = exe.submit(callable2);
        Future<Float> future3 = exe.submit(callable3);
        Future<Float> future4 = exe.submit(callable4);
        list.add(future1);
        list.add(future2);
        list.add(future3);
        list.add(future4);


        float fResultado = 0;
        for(Future<Float> cal : list) {
            try{ 
                fResultado += cal.get();
            } catch(InterruptedException | ExecutionException ee) {}
        }

        exe.shutdown();
        while(!exe.isTerminated());

        System.out.println("El resultado es " + fResultado);
    }


}