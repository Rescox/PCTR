import java.util.concurrent.Semaphore;
import java.util.concurrent.*;


class ProdCons{
    private int[] buffer;
    private int iIndex = 0, iFindex = 0;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore isNotEmpty = new Semaphore(0, true);
    private Semaphore isNotFull;

    public int getBuffer(int iIndex)
    {
        return this.buffer[iIndex];
    }

    public ProdCons(int iTam) {
        buffer = new int[iTam];
        isNotFull = new Semaphore(buffer.length, true);
    }

    public void Producir(int iProductos) throws InterruptedException
    {
        isNotFull.acquire();
        mutex.acquire();
        buffer[iIndex] = iProductos;
        System.out.println("Productor produce "+ iProductos);
        iIndex = (iIndex + 1) % buffer.length;
        mutex.release();
        isNotEmpty.release();
    }  

    public int Consumir() throws InterruptedException { 
        isNotEmpty.acquire();
        mutex.acquire();
        int iAux = iFindex;
        iFindex = (iFindex + 1) % buffer.length;
        System.out.println("Consumidor consume " + buffer[iAux]);
        buffer[iAux] = 0;
        mutex.release();
        isNotFull.release();
        return buffer[iAux];
    }


    public static void main(String[] args) throws Exception {
        ProdCons buffer = new ProdCons(10);
        Productor p = new Productor(buffer, 100);
        Consumidor c = new Consumidor(buffer, 100);
        Thread hiloProductor = new Thread(p);
        Thread hiloConsumidor = new Thread(c);
        hiloProductor.start();
        hiloConsumidor.start();
        hiloProductor.join();
        hiloConsumidor.join();
        System.out.println("------------------------------");
        System.out.println("El resultado del buffer es: ");
        for(int i = 0; i < 10; i++)
            System.out.print("[" + buffer.getBuffer(i) + "]");
    }

}