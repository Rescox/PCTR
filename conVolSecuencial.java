import java.util.*;

public class conVolSecuencial {
    public static int N = 100000;
    public static int [][] mEnfocar =          {{0,-1,0},
                                        {-1, 5, -1},
                                        {0, -1, 0}};

    public static int [][] mDesEnfocar =       {{1,1,1},
                                        {1, 1, 1},
                                        {1, 1, 1}};

    public static int [][] mRealceBordes =     {{0, 0, 0},
                                        {-1, 1, 0},
                                        {0, 0, 0}};
    
    public static int [][] mDetectarBordes =   {{0, 1, 0},
                                        {1, -4, 1},
                                        {0, 1, 0}};

    public static int [][] mSobel =            {{-1, 0, 1},
                                        {-2, 0, 2},
                                        {-1, 0, 1}};

    public static int [][] mSharpen =          {{1, -2, 1},
                                        {-2, 5, -2},
                                        {1, -2, 1}};

    static void convolucion(int [][] mMatrizSinTransformar, int [][] mTransformador, int iIndex, int iFindex) {
        for(int i = iIndex; i < iFindex; i++) {
            for(int j = iIndex; j < iFindex; j++) {
                for(int k = 0; k < 3 ; k++) { 
                    for(int l = 0; l < 3 ; l++) { 
                        try{ 
                            if (mMatrizSinTransformar[i][j] > 20)
                                mMatrizSinTransformar[i][j] = 20;
                            else if(mMatrizSinTransformar[i][j] < -20)
                                mMatrizSinTransformar[i][j] = -20;
                            else
                                if(i > 0 && j > 0)
                                    mMatrizSinTransformar[i][j] += mMatrizSinTransformar[i -1][j - 1] * mTransformador[k][l];
                        } catch(Exception e) { e.printStackTrace(); }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        int [][] mFoto = new int[10000][10000];
        Random r = new Random();
        for(int i = 0; i < 10000; i++) {
            for(int j = 0; j < 10000; j++) {
                mFoto[i][j] = r.nextInt((20 - (-20)) + 1) - 20;
            }
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Elija que opción desea realizar");
        System.out.println("1 - Enfocar");
        System.out.println("2 - Desenfocar");
        System.out.println("3 - Realzar bordes");
        System.out.println("4 - Detectar bordes");
        System.out.println("5 - Nose que de Bobel");
        System.out.println("6 - Nose que de Sharpen");
        int iOpcion = sc.nextInt();
        System.out.println("Seleccione un rango comprendido entre 0 y 10000");
        int inicio = sc.nextInt();
        int fin = sc.nextInt();
        sc.close();
        long start, elapsedTime = 0;
        switch(iOpcion) {
            case 1:
                start = System.currentTimeMillis();    
                convolucion(mFoto, mEnfocar, inicio, fin);
                elapsedTime = System.currentTimeMillis() - start;
                
                break;
            case 2:
                start = System.currentTimeMillis(); 
                convolucion(mFoto, mDesEnfocar, inicio, fin);
                elapsedTime = System.currentTimeMillis() - start;
                break;
            case 3:
                start = System.currentTimeMillis(); 
                convolucion(mFoto, mRealceBordes, inicio, fin);
                elapsedTime = System.currentTimeMillis() - start;
                break;
            case 4:
                start = System.currentTimeMillis(); 
                convolucion(mFoto, mDetectarBordes, inicio, fin);
                elapsedTime = System.currentTimeMillis() - start;
                break;
            case 5:
                start = System.currentTimeMillis(); 
                convolucion(mFoto, mSobel, inicio, fin);
                elapsedTime = System.currentTimeMillis() - start;
                break;
            case 6:
                start = System.currentTimeMillis(); 
                convolucion(mFoto, mSharpen, inicio, fin);
                elapsedTime = System.currentTimeMillis() - start;
                break;
        }
        System.out.println("Ha tardado la convolucion: " + elapsedTime/1000F);

    }
}