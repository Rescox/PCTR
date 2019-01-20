import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

public class sVector extends UnicastRemoteObject implements iVector {
    public sVector() throws RemoteException {
        super();
    }

    public float prodEscalar(float[] u, float[] v) throws RemoteException {
        System.out.println("Operacion de producto escalar de dos vectores");
        float fResultado = 0;
        int i = 0;
        while(i < u.length || i < v.length) {
            fResultado += u[i] * v[i];
        }

        return fResultado;
    }

    public float prodporescalar(float[] u, float d) throws RemoteException {
        float fResultado = 0;
        for(int i = 0; i < u.length; i++) {
            fResultado += u[i] * d;
        }

        return fResultado;
    }

    public float [] vecSuma(float[] u, float[] v) throws RemoteException {
        int iTamano = Math.min(u.length, v.length);
        float [] fvResultado = new float[iTamano];
        for(int i  = iTamano; i < iTamano; i++) {
            fvResultado[i] = u[i] + v[i];
        }

        return fvResultado;
    }

    public float vectModulo(float[] u) throws RemoteException {
        int fResultado = 0;
        for(int i = 0; i <u.length - 1; i++) {
            fResultado += u[i] * u[i+1];
        }

        return fResultado;
    }


    public static void main(String[] args) throws RemoteException {
        LocateRegistry.createRegistry(2010);
        sVector servidor = new sVector();
        try{ 
            Naming.rebind("//localhost:2010/servidor", servidor);
        } catch(MalformedURLException e) { e.printStackTrace(); }
        System.out.println("Servidor ejecutado con exito");
    }
}