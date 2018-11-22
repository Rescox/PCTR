import java.util.Random;

public class Productor implements Runnable{
    private Random r = new Random();
    private ProdCons buffer;
    private int iIteraciones;
    
    public Productor(ProdCons b, int i)
    {
        buffer = b;
        iIteraciones = i;
    }

    @Override
    public void run() {
        try{ 
        for(int i = 0; i < iIteraciones; i++)
        {
            int aux = r.nextInt(100);
            buffer.Producir(aux);
        }
        }catch(Exception ee) { System.out.println("a");}
    }

}