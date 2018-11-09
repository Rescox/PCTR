#include <iostream> 
#include <thread> 
#include <mutex>
using namespace std; 

mutex m;

class Mutexs {
  public:
    Mutexs(int i, bool b):_iIteraciones(i), _op(b) {}
    void sumar_restar(); 
    int getiContador(){return _iContador;}

  private:
    static int _iContador; 
    int _iIteraciones; 
    bool _op; 
}; 

int main() {
    Mutexs mutexs_1(100000, true); 
    Mutexs mutexs_2(100000, false); 
    thread Hilo1(&Mutexs::sumar_restar,  & mutexs_1); 
    thread Hilo2(&Mutexs::sumar_restar,  & mutexs_2); 
    Hilo1.join(); 
    Hilo2.join(); 
    cout << mutexs_1.getiContador() << endl;
}

int Mutexs::_iContador = 0; 

void Mutexs::sumar_restar() {
    try {
        m.lock();
        switch (_op) {
        case true:
            for (int i = 0; i < _iIteraciones; i++)
                _iContador++; 
            break; 

        case false:
            for (int i = 0; i < _iIteraciones; i++)
                _iContador--; 
            break; 
        }
        m.unlock();
    }catch (exception e) {cout << "asdasdasdasd " << endl; }
    
}