public class Consumidor implements Runnable { 
    private ProdCons buffer;
    private int iIteraciones;
    
    public Consumidor(ProdCons b, int i)
    {
        buffer = b;
        iIteraciones = i;
    }

    @Override
    public void run() {
        try{ 
        for(int i = 0; i < iIteraciones; i++)
        {
            int aux = buffer.Consumir();
        }
        }catch(Exception ee) { System.out.println("a");}
    }

}
