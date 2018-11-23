import java.util.concurrent.*;
import java.beans.IntrospectionException;
import java.util.*;

class Viking_Hambriento implements Runnable {
    private static drakkarVikingo dv;
    Viking_Hambriento(drakkarVikingo dV) {
        this.dv = dV;
    }

    public void run() {
        dv.Comer();
    }    
}

class Vikingo_Cocinero implements Runnable {
    
    private static drakkarVikingo dv;
    private int anguilas;
    Vikingo_Cocinero(drakkarVikingo dV, int m) {
        this.dv = dV;
        this.anguilas = m;
    }

    public void run() {
        dv.Cocinar(anguilas);
    }    
}

 
public class drakkarVikingo{
    private static int anguilas;

    drakkarVikingo(int m) { anguilas = m; }
    
    public synchronized void Comer() {
        while (true){ 
            while(eneldoVacio()) {
                try{ 
                    wait();
                } catch (InterruptedException ee) { System.out.print("Error"); }
            }
            System.out.println("El vikingo "+ Thread.currentThread().getId() +" esta comiendo...");
            anguilas--;
            System.out.println("Quedan " + anguilas + " anguilas");
            notifyAll();
        }
    }

    public synchronized void Cocinar(int m) {
        while (true) {   
            try{   
            while(!eneldoVacio()){
                System.out.println("Cocinero espera...");
                    wait();
            }
            Thread.sleep(1500);
            anguilas = m;
            notifyAll();
            } catch (InterruptedException ee) { System.out.print("Error"); }
        }
    }

    public boolean eneldoVacio() {
        return (anguilas==0);
    }

    public static void main(String[] args) throws InterruptedException {
        int anguilas = 100;
        drakkarVikingo dv = new drakkarVikingo(anguilas);

        Thread cocinero = new Thread(new Vikingo_Cocinero(dv,anguilas));
        Thread[] vikingos = new Thread[4];
        for(int i = 0; i < 4; i++) {
            vikingos[i] = new Thread(new Viking_Hambriento(dv));
            vikingos[i].start();
        }
        cocinero.start();
        for(int i = 0; i < 4; i++) {
            vikingos[i].join();
        }
        cocinero.join();
    }



}