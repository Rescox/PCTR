import java.util.*;
import java.util.concurrent.*;

public class conVolParalelo implements Runnable{
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

    public int [][] mHilosTransformador;
    public int iInicio;
    public int iFinal;
    public static int[][] mFoto;

    public conVolParalelo(int[][] mMatrizTransformadora, int i, int f) {
        mHilosTransformador = mMatrizTransformadora;
        iInicio = i;
        iFinal = f;
    }

    public void run() {
        for(int i = iInicio; i < iFinal; i++) {
            for(int j = iInicio; j < iFinal; j++) {
                    for(int k = 0; k < 3 ; k++) { 
                        for(int l = 0; l < 3 ; l++) { 
                            if (mFoto[i][j] > 20)
                                mFoto[i][j] = 20;
                            else if(mFoto[i][j] < -20)
                                mFoto[i][j] = -20;
                            else { 
                                if(i > 0 && j > 0)
                                    mFoto[i][j] += mFoto[i  -1][j - 1] * mHilosTransformador[k][l];
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        mFoto = new int[10000][10000];
        Random r = new Random();
        for(int i = 0; i < 10000; i++) {
            for(int j = 0; j < 10000; j++) {
                mFoto[i][j] = r.nextInt((20 - (-20)) + 1) - 20;
            }
        }
        int iTareas = Integer.parseInt(args[0]);
        ExecutorService exe = Executors.newFixedThreadPool(iTareas);
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
        int iIntervalo = (fin-inicio)/iTareas;
        sc.close();
        long start = 0, elapsedTime = 0;
        switch(iOpcion) {
            case 1:
                start = System.currentTimeMillis();
                for(int i = 0; i < iTareas; i++) {
                    if(i == iTareas - 1 && iIntervalo*(i+1)  % fin == 0) { 
                        exe.execute(new conVolParalelo(mEnfocar, inicio, fin));
                        System.out.println(" y las sobras");
                    }
                    else { 
                        exe.execute(new conVolParalelo(mEnfocar, inicio, iIntervalo*(i+1) - 1));
                        System.out.println(" El intervalo es " + iIntervalo);
                    }
                    inicio += iIntervalo;
                }
                break;
            case 2:
                start = System.currentTimeMillis();
                for(int i = 0; i < iTareas; i++) {
                    if(i == iTareas - 1 && (iIntervalo*(i+1) - 1) % fin == 0) { 
                        exe.execute(new conVolParalelo(mDesEnfocar, inicio + iIntervalo*i, fin));
                    }
                    else { 
                        exe.execute(new conVolParalelo(mDesEnfocar, inicio , iIntervalo*i+1 - 1));
                    }
                    inicio += iIntervalo*i;
                }
                break;
            case 3:
                start = System.currentTimeMillis();
                for(int i = 0; i < iTareas; i++) {
                    if(i == iTareas - 1 && (iIntervalo*(i+1) - 1) % fin == 0) { 
                        exe.execute(new conVolParalelo(mRealceBordes, inicio + iIntervalo*i, fin));
                    }
                    else { 
                        exe.execute(new conVolParalelo(mRealceBordes, inicio , iIntervalo*i+1 - 1));
                    }
                    inicio += iIntervalo;
            }
            break;
            case 4:
                start = System.currentTimeMillis();
                for(int i = 0; i < iTareas; i++) {
                    if(i == iTareas - 1 && (iIntervalo*(i+1) - 1) % fin == 0) { 
                        exe.execute(new conVolParalelo(mDetectarBordes, inicio + iIntervalo*i, fin));
                    }
                    else { 
                        exe.execute(new conVolParalelo(mDetectarBordes, inicio , iIntervalo*i+1 - 1));
                    }
                inicio += iIntervalo*i;
            }
            break;
            case 5:
                start = System.currentTimeMillis();
                for(int i = 0; i < iTareas; i++) {
                    if(i == iTareas - 1 && (iIntervalo*(i+1) - 1) % fin == 0) { 
                        exe.execute(new conVolParalelo(mSobel, inicio + iIntervalo*i, fin));
                    }
                    else { 
                        exe.execute(new conVolParalelo(mSobel, inicio , iIntervalo*i+1 - 1));
                    }
                inicio += iIntervalo*i;
            }
            break;
            case 6:
                start = System.currentTimeMillis();
                for(int i = 0; i < iTareas; i++) {
                    if(i == iTareas - 1 && (iIntervalo*(i+1) - 1) % fin == 0) { 
                        exe.execute(new conVolParalelo(mSharpen, inicio + iIntervalo*i, fin));
                    }
                    else { 
                        exe.execute(new conVolParalelo(mSharpen, inicio , iIntervalo*i+1 - 1));
                    }
                inicio += iIntervalo*i;
            }
            break;
        }
        exe.shutdown();
        while(!exe.isTerminated());
        elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Ha tardado la convolucion: " + elapsedTime/1000F);
    }
}