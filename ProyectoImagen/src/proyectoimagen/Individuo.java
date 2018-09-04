
package proyectoimagen;

import java.util.Random;

public class Individuo {
    
    int informacionRojo [];
    int informacionVerde [];
    int informacionAzul [];
    
    int adaptabilidad;
    int tamanno;
    
    
    /*
    A considerar: 
    *   Separar los algoritmos de crear inicialmente y crearDescendencia puede no importar en teoria.
    *   Buscar forma de defenir tamanno solo una vez.
    */
    Individuo(int tamanno){
        //this.tamanno = tamanno * tamanno;
        this.tamanno = 32 * 32;
        
        informacionAzul = new int[this.tamanno];
        informacionRojo = new int[this.tamanno];
        informacionVerde = new int[this.tamanno];
    }
    
    
    //      Creacion de objeto
    
    /*
    Nota:   La imagen meta se ocupa cada vez que se quiera crear.
            Buscar alternativa.
    */
    /*
    Algoritmo que crea un individuo con informacion random.
    */
    public void formarIndividuoInicial(int imagenMeta){
        
        Random numeroRandom = new Random();
        
        for(int indice = 0; indice < tamanno ; ++indice){
            this.informacionAzul[indice] = numeroRandom.nextInt(256);
            this.informacionRojo[indice] = numeroRandom.nextInt(256);
            this.informacionVerde[indice] = numeroRandom.nextInt(256);
        }
        
        calcularAdaptabilidad(imagenMeta);
    }
    
    /*
    Algoritmo de mutacion y decendencia.
    */
    public void crearDescendencia(Individuo primerOrigen, Individuo segundoOrigen, int imagenMeta){
        calcularAdaptabilidad(imagenMeta);
    }
    
    
    
    
    //      Calculo de variables.
    
    /* 
    A considerar: se puede hacer le parametro de imagen meta un atributo del objeto por el constructor.
    Esto podria mejorar tiempos.
    */
    private void calcularAdaptabilidad(int imagenMeta){
        this.adaptabilidad = 0;
    }
    
    // Metodo Get de adaptabilidad.
    public int obtenerAdaptabilidad(){
        return this.adaptabilidad;
    }
    
    /*
    Nota: Encontrar forma de almacenar los tres en uno, quitando el if.
    
    Obtiene elemento de la matriz guardada como arreglo.
    Numeros colores:
    0 = azul
    1 = rojo
    2 = verde
    */
    private int obtenerElemento(int x, int y, int color){
        int elemento = 0;
        int indice = y * 32 + x;
        
        if(color == 0){
            elemento = this.informacionAzul[indice];
        } else if(color == 1){
            elemento = this.informacionRojo[indice];
        } else {
            elemento = this.informacionVerde[indice];
        }
        
        return elemento;
    }
    
}
