package proyectoimagen;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class AlgoritmoGenetico {
    
    int imagenMeta [];
    int dimensionImagen[];
    Individuo poblacion [];
    int tamannoPoblacion;
    
    
    AlgoritmoGenetico(String rutaImagenMeta, int generaciones, int tamannoPoblacion, int imagenX, int imagenY){
        
        this.imagenMeta = leerImagenMeta(rutaImagenMeta);
        this.tamannoPoblacion = tamannoPoblacion;
        this.poblacion = new Individuo[tamannoPoblacion];
        
        this.dimensionImagen = new int[2];
        this.dimensionImagen[0] = imagenX;
        this.dimensionImagen[1] = imagenY;
        
        crearPoblacion();
        for(int generacion = 1; generacion<generaciones ; ++generacion){
            funcionDeAdaptabilidad();
            multiplicacionDeIndividuos();
        }
        
    }
    
    /*
    *   Lee la imagen meta a partir de una ruta.
    La guarda como un arreglo con estructura de matriz. (Formula: (nColumnas-1) * y + x)
    Utiliza la clase ImageIO, cada dato posee el modelo rgb en bits. FF(S) FF(R) FF(G) FF(B).
    Devuelve el arreglo nuevo con los datos de la imagen.
    */
    private int[] leerImagenMeta(String ruta){
        BufferedImage imagenMeta = null;
        
        try {
            imagenMeta = ImageIO.read(ProyectoImagen.class.getResource(ruta));
        } catch (IOException ex) {
            Logger.getLogger(AlgoritmoGenetico.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        int height = imagenMeta.getHeight();
        int width = imagenMeta.getWidth();
        int imagenMetaRGB [] = new int[height * width];
        
        for(int x = 0; x < height; ++x){
            for(int y = 0; y < width; ++y){
                imagenMetaRGB[(height-1) * y + x] = imagenMeta.getRGB(x, y);
            }
        }
        return imagenMetaRGB;
        
    }
    
    private void crearPoblacion(){
        for(int individuoActual = 0; individuoActual<tamannoPoblacion ; ++ individuoActual){
            poblacion[individuoActual] = new Individuo(this.dimensionImagen[0], this.dimensionImagen[1]);
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
                poblacion[nuevoIndividuo] = new Individuo(this.dimensionImagen[0], this.dimensionImagen[1]);
                poblacion[nuevoIndividuo].crearDescendencia(poblacion[parActual], poblacion[parActual+1], this.imagenMeta, 10);
            }
        }
        
    }
    
    
}
