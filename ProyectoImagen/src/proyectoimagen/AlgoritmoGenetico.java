package proyectoimagen;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


import libreria.QuickSort;

public class AlgoritmoGenetico {
    
    int imagenMeta [][];
    int dimensionImagen[];
    Individuo poblacion [];
    int tamannoPoblacion;
    Individuo evolucion[];
    int tipoDistancia;
    
    /*
    Tipo distancia:
    0 - euclideana
    1 - manhattan
    */
    AlgoritmoGenetico(String rutaImagenMeta, int generaciones, int tamannoPoblacion, int imagenX, int imagenY, int tipoDistancia){
        
        this.imagenMeta = leerImagenMeta(rutaImagenMeta);
        System.out.println("Se leyo la imagen meta.");
        this.tamannoPoblacion = tamannoPoblacion;
        this.poblacion = new Individuo[tamannoPoblacion];
        
        this.dimensionImagen = new int[2];
        this.dimensionImagen[0] = imagenX;
        this.dimensionImagen[1] = imagenY;
        this.tipoDistancia = tipoDistancia;
        
        
        
        /*
        * Inicia el algoritmo genetico.
        */
        double mejoresIndividuos = 0.1 * generaciones;
        this.evolucion = new Individuo[generaciones / (int)mejoresIndividuos];
        
        crearPoblacion();
        
        //debug
        funcionDeAdaptabilidad();
        imprimir();
        
        for(int indice = 0; indice < this.tamannoPoblacion ; ++ indice){
            System.out.print(poblacion[indice].obtenerAdaptabilidad() + "\t");
        }
        System.err.println("\n");
        
        //fin debug
        
        
        System.out.println("Inicia algoritmo.");
        for(int generacion = 1; generacion<generaciones ; ++generacion){ 
            funcionDeAdaptabilidad();
            multiplicacionDeIndividuos();
            System.out.println("Generacion " + generacion + " terminada.");
        }
        
        System.out.println("Termina algoritmo.");
        imprimirFinal();
        
    }
    
    
    /*Nota: quitar metodo*/
    private void imprimir(){
        
        Individuo a = this.poblacion[0];
        
        BufferedImage siguienteImagen = new BufferedImage(a.tammanoY, a.tammanoX, BufferedImage.TYPE_INT_ARGB);
        
        int color = 0;
        
        for(int x = 0; x < a.tammanoY; ++x){
            for(int y = 0; y < a.tammanoX; ++y){
                color = a.informacionRGB[a.tammanoY*y + x];
                siguienteImagen.setRGB(x, y, color);
                //System.out.print(Integer.toBinaryString(siguienteImagen.getRGB(x, y)) + "\t");
            }
            //System.out.println("\n");
        }
        System.out.println("Individuo guardado");
        
        
        File imagenFinal = new File("D:\\entrada.png");
        try {
            ImageIO.write(siguienteImagen, "png", imagenFinal);
        } catch (IOException ex) {
            Logger.getLogger(Individuo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    *   Lee la imagen meta a partir de una ruta.
    La guarda como un arreglo con estructura de matriz. (Formula: (nColumnas-1) * y + x)
    Utiliza la clase ImageIO, cada dato posee el modelo rgb en bits. FF(S) FF(R) FF(G) FF(B).
    Devuelve el arreglo nuevo con los datos de la imagen.
    */
    private int[][] leerImagenMeta(String ruta){
        BufferedImage imagenMeta = null;
        
        try {
            imagenMeta = ImageIO.read(ProyectoImagen.class.getResource(ruta));
        } catch (IOException ex) {
            Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        int height = imagenMeta.getHeight();
        int width = imagenMeta.getWidth();
        int imagenMetaRGB [][] = new int[height][width];
        
        for(int x = 0; x < height; ++x){
            for(int y = 0; y < width; ++y){
                imagenMetaRGB[x][y] = imagenMeta.getRGB(x, y);
                //System.out.print(Integer.toBinaryString(imagenMeta.getRGB(x, y)) + "\t");
            }
            //System.out.println("\n");
        }
        return imagenMetaRGB;
        
    }
    
    private void crearPoblacion(){
        for(int individuoActual = 0; individuoActual<tamannoPoblacion ; ++ individuoActual){
            poblacion[individuoActual] = new Individuo(this.dimensionImagen[0], this.dimensionImagen[1]);
            poblacion[individuoActual].formarIndividuoInicial(imagenMeta, tipoDistancia);
        }
    }
    
    /*
    Ordena los individuos por adaptabilidad.
    Mayor a menor.
    */
   
    private void quickSort(int primero, int ultimo) 
    {
        int i = primero;
        int j = ultimo;

        // index medio de la lista
        double pivote = poblacion[primero + (ultimo - primero) / 2].obtenerAdaptabilidad();

        // divide en dos arrays
        while (i <= j) {
            /**
             * en cada iteración se identificara los numeros de cada lado(izq y der)
             * dado el menor y el mayor respectivamente dependiendo del pivote para
             * la comparación. Una vez completi, se puede hacer el intercambio de 
             * ambos números
             */
            while (poblacion[i].obtenerAdaptabilidad() < pivote) {
                i++;
            }
            while (poblacion[j].obtenerAdaptabilidad() > pivote) {
                j--;
            }
            if (i <= j) {
                intercambioQuickSort(i, j);
                // mueve los indices de ambos lados
                i++;
                j--;
            }
        }

        // llamado recursivo
        if (primero < j) {
            quickSort(primero, j);
        }

        if (i < ultimo) {
            quickSort(i, ultimo);
        }
    }

    private void intercambioQuickSort(int primero, int ultimo) 
    {
        Individuo variable_temporal = poblacion[primero];
        poblacion[primero] = poblacion[ultimo];
        poblacion[ultimo] = variable_temporal;
    }
    
    
    private void algoritmoDeOrdenamiento(){
        quickSort(0, tamannoPoblacion-1);
        
        for(int indice = 0; indice < this.tamannoPoblacion ; ++ indice){
            
            System.out.print(poblacion[indice].obtenerAdaptabilidad() + "\t");
        }
        System.out.println("\n");
    
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
        
        int indiceGeneracionPasada = 20; //indice en el elemento despues de la generacion pasada que se desea mantener
        int cantidadDescendencia = this.tamannoPoblacion - indiceGeneracionPasada;
        int cantidadNuevaPoblacion = cantidadDescendencia / (indiceGeneracionPasada/2);
        int cantidadViejaPoblacion = indiceGeneracionPasada;
        
        for(int parActual = 0; parActual < cantidadViejaPoblacion ; parActual = parActual + 2){
            //System.out.println(parActual);
            //System.out.println("primer " + poblacion[parActual].obtenerAdaptabilidad());
            //System.out.println("segundo " + poblacion[parActual+1].obtenerAdaptabilidad());
                
            for(int cantidadNuevoIndividuo = 1; cantidadNuevoIndividuo <= cantidadNuevaPoblacion ; ++cantidadNuevoIndividuo){
                poblacion[indiceGeneracionPasada] = new Individuo(this.dimensionImagen[0], this.dimensionImagen[1]);
                poblacion[indiceGeneracionPasada].crearDescendencia(poblacion[parActual], poblacion[parActual+1], this.imagenMeta, 50, tipoDistancia);
                
                //System.out.println("indice: " + indiceGeneracionPasada + " descendiente numero " + cantidadNuevoIndividuo + ": " + poblacion[indiceGeneracionPasada].obtenerAdaptabilidad());
                indiceGeneracionPasada = indiceGeneracionPasada + 1;
            }
            //System.out.println("\n");
        }
              
    }
    
    
    private void imprimirFinal(){
        for(int indice = 0; indice < this.tamannoPoblacion ; ++ indice){
            System.out.print(poblacion[indice].obtenerAdaptabilidad() + "\t");
        }
        System.err.println("\n");
       
        poblacion[0].guardarIndividuo();
    }
    
}
