import java.rmi.*;
import java.rmi.registry.*;

public class cVector {
    public static void main(String[] args) throws Exception{ 
        
        iVector refServer = (iVector) Naming.lookup("//localhost:2010/servidor");
        
        float [] v1 = {1, 2, 3};
        float [] v2 = {1, 2, 3};

        float resultado = refServer.prodEscalar(v1,v2);
    }
}