#include <iostream>
#include <cstdlib>
#include <ctime>
#include <thread>
#include <vector>

using namespace std;


static int mFoto[10000][10000];


void convolucion(int mTransformador[][3], int iIndex, int iFindex) {
    for(int i = iIndex; i < iFindex; i++) {
        for(int j = iIndex; j < iFindex; j++) {
            for(int k = 0; k < 3 ; k++) { 
                for(int l = 0; l < 3 ; l++) { 
                    try{ 
                        if (mFoto[i][j] > 20)
                            mFoto[i][j] = 20;
                        else if(mFoto[i][j] < -20)
                            mFoto[i][j] = -20;
                        else
                            if(i > 0 && j > 0)
                                mFoto[i][j] += mFoto[i -1][j - 1] * mTransformador[k][l];
                    } catch(int e) { }
                }
            }
        }
    }
}

int main(int argc, char **argv) {
    unsigned t0, t1;
    srand(0);
    int mEnfocar[3][3] =         {{0,-1, 0},
                                {-1, 5, -1},
                                {0, -1, 0}};

    int mDesEnfocar[3][3] =      {{1, 1, 1},
                                {1, 1, 1},
                                {1, 1, 1}};

    int mRealceBordes[3][3] =    {{0, 0, 0},
                                {-1, 1, 0},
                                {0, 0, 0}};
    
    int mDetectarBordes[3][3] =  {{0, 1, 0},
                                {1, -4, 1},
                                {0, 1, 0}};

    int mSobel[3][3] =           {{-1, 0, 1},
                                {-2, 0, 2},
                                {-1, 0, 1}};

    int mSharpen[3][3] =         {{1, -2, 1},
                                {-2, 5, -2},
                                {1, -2, 1}};

    for(int i = 0; i < 10000; i++) {
        for(int j = 0; j < 10000; j++) {
            mFoto[i][j] = rand()%21;
        }
    }
    int iTareas = atoi(argv[1]);
    vector<thread> hilos;


    
    
    int iOpcion;
    std::cout << "Elija que opción desea realizar" << std::endl;
    std::cout << "1 - Enfocar" << std::endl;
    std::cout << "2 - Desenfocar" << std::endl;
    std::cout << "3 - Realzar bordes" << std::endl;
    std::cout << "4 - Detectar bordes" << std::endl;
    std::cout << "5 - Nose que de Bobel" << std::endl;
    std::cout << "6 - Nose que de Sharpen" << std::endl;
    std::cin >> iOpcion;
    std::cout <<"Seleccione un rango comprendido entre 0 y 10000" << std::endl;
    int inicio, fin; 
    std::cin >> inicio;
    std::cin >> fin;

    double time;
    int iIntervalo = (fin-inicio)/iTareas;

    switch(iOpcion) {
            case 1:   
                t0=clock();
                
                for(int i = 0; i < iTareas; i++) { 
                    if(i == (iTareas - 1))
                        hilos.push_back(std::thread(convolucion, mEnfocar, inicio, fin));
                    else
                        hilos.push_back(std::thread(convolucion, mEnfocar,  inicio, iIntervalo*(i*1) - 1));
                    inicio += iIntervalo;
                }

                for(int i = 0; i < iTareas; i++) { 
                    hilos[i].join();
                }

                t1=clock();
                time = (double(t1-t0)/CLOCKS_PER_SEC);
                break;

            case 2: 
                t0=clock();
                for(int i = 0; i < iTareas; i++) { 
                    if(i == (iTareas - 1))
                        hilos.push_back(std::thread(convolucion, mDesEnfocar, inicio, fin));
                    else
                        hilos.push_back(std::thread(convolucion, mDesEnfocar,  inicio, iIntervalo*(i*1) - 1));
                    inicio += iIntervalo;
                }

                for(int i = 0; i < iTareas; i++) { 
                    hilos[i].join();
                }
                t1=clock();
                time = (double(t1-t0)/CLOCKS_PER_SEC);
                break;

            case 3: 
                t0=clock();
                for(int i = 0; i < iTareas; i++) { 
                    if(i == (iTareas - 1))
                        hilos.push_back(std::thread(convolucion, mRealceBordes, inicio, fin));
                    else
                        hilos.push_back(std::thread(convolucion, mRealceBordes,  inicio, iIntervalo*(i*1) - 1));
                    inicio += iIntervalo;
                }

                for(int i = 0; i < iTareas; i++) { 
                    hilos[i].join();
                }
                t1=clock();
                time = (double(t1-t0)/CLOCKS_PER_SEC);
                break;
            case 4: 
                t0=clock();
                for(int i = 0; i < iTareas; i++) { 
                    if(i == (iTareas - 1))
                        hilos.push_back(std::thread(convolucion, mDetectarBordes, inicio, fin));
                    else
                        hilos.push_back(std::thread(convolucion, mDetectarBordes,  inicio, iIntervalo*(i*1) - 1));
                    inicio += iIntervalo;
                }

                for(int i = 0; i < iTareas; i++) { 
                    hilos[i].join();
                }
                t1=clock();
                time = (double(t1-t0)/CLOCKS_PER_SEC);
                break;
            case 5: 
                t0=clock();
                for(int i = 0; i < iTareas; i++) { 
                    if(i == (iTareas - 1))
                        hilos.push_back(std::thread(convolucion, mSobel, inicio, fin));
                    else
                        hilos.push_back(std::thread(convolucion, mSobel,  inicio, iIntervalo*(i*1) - 1));
                    inicio += iIntervalo;
                }

                for(int i = 0; i < iTareas; i++) { 
                    hilos[i].join();
                }
                t1=clock();
                time = (double(t1-t0)/CLOCKS_PER_SEC);
                break;
            case 6: 
                t0=clock();
                for(int i = 0; i < iTareas; i++) { 
                    if(i == (iTareas - 1))
                        hilos.push_back(std::thread(convolucion, mSharpen, inicio, fin));
                    else
                        hilos.push_back(std::thread(convolucion, mSharpen,  inicio, iIntervalo*(i*1) - 1));
                    inicio += iIntervalo;
                }

                for(int i = 0; i < iTareas; i++) { 
                    hilos[i].join();
                }
               t1=clock();
                time = (double(t1-t0)/CLOCKS_PER_SEC);
                break;
        }

        std::cout << "Se ha tardado " << time << std::endl;
}