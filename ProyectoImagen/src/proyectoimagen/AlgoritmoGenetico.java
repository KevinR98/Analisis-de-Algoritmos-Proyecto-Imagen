package proyectoimagen;

public class AlgoritmoGenetico {
    
    int imagenMeta;
    int tamannoPoblacion;    
    Individuo poblacion [];
    
    
    AlgoritmoGenetico(int imagenMeta, int generaciones, int tamannoPoblacion){
        
        this.imagenMeta = imagenMeta;
        this.tamannoPoblacion = tamannoPoblacion;
        poblacion = new Individuo[tamannoPoblacion];
        
        crearPoblacion();
        for(int generacion = 1; generacion<generaciones ; ++generacion){
            funcionDeAdaptabilidad();
            multiplicacionDeIndividuos();
        }
        
    }
    
    private void crearPoblacion(){
        for(int individuoActual = 0; individuoActual<tamannoPoblacion ; ++ individuoActual){
            poblacion[individuoActual] = new Individuo(32);
            poblacion[individuoActual].formarIndividuoInicial(imagenMeta);
        }
    }
    
    /*
    Ordena los individuos por adaptabilidad.
    Mayor a menor.
    */
    private void algoritmoDeOrdenamiento(){
        
    }
    
    /*
    Nota: 
    posible solucion para condicion de parada
    utilzar el primer elemento del arreglo poblacion,
    si su adaptabilidad es acercada al maximo, detener.
    
    Escoge los individuos mas adaptables.
    */
    private void funcionDeAdaptabilidad(){
        algoritmoDeOrdenamiento();
        
    }
    
    
    /*
    
    Nota: Se puede mejorar el proceso de union de parejas,
    comparando una por cada elemento de las otras, la que es mas
    diferente es la que toma como pareja, se usa la funcion de adaptabilidad.
    
    Cada nueva generacion conserva los individuos mas adaptables de la generacion pasada.
    
    La cantidad de individuos mas adaptables a usar es cantidadGeneracionPasada.
    
    La cantidad de descendencia debe matener el tamaño de la poblacion.
    Para cumplir la anterior regla se debe escoger una cantidad de la generacion pasada que sea
    multiplo de la diferencia entre la generacion pasada y la nueva, esto debido a que el numero
    debe ser entero.
    
    */
    private void multiplicacionDeIndividuos(){
        
        int cantidadGeneracionPasada = 20;
        int cantidadDescendencia = this.tamannoPoblacion - cantidadGeneracionPasada;
        int cantidadNuevaPoblacion = cantidadDescendencia / (cantidadGeneracionPasada/2);
        
        for(int parActual = 0; parActual < cantidadGeneracionPasada/2 ; parActual = parActual + 2){
            for(int nuevoIndividuo = 0; nuevoIndividuo < cantidadNuevaPoblacion ; ++nuevoIndividuo){
                poblacion[nuevoIndividuo] = new Individuo(32);
                poblacion[nuevoIndividuo].crearDescendencia(poblacion[parActual], poblacion[parActual+1], this.imagenMeta, 10);
            }
        }
        
    }
    
    
}
